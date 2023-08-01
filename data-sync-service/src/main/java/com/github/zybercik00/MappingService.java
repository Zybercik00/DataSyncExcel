package com.github.zybercik00;

import com.github.zybercik00.domain.proces.Extraction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.metamodel.EntityType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MappingService {
    private final EntityManager entityManager;

    @SneakyThrows
    <T> T findEntityBy(Class<T> entityClass, Map<String, Object> lookup) {
        String entityName = getEntityName(entityClass);
        String filters = lookup.keySet()
                .stream()
                .map(attribute ->
                        "e" + "." + attribute + "=:" + attribute)
                .collect(Collectors.joining(" and "));
        TypedQuery<T> query = entityManager.createQuery(
                "select " + "e"+" from " + entityName + " " + "e" + " " +
                        "where " + filters, entityClass);

        T entity = entityClass.getConstructor().newInstance();
        for (Map.Entry<String, Object> entry : lookup.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object valueReplace = getValueReplace(entity, key, value);
            // Value replace
            query.setParameter(key, valueReplace);

            BeanUtils.setProperty(entity, key, valueReplace);
        }

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            entityManager.persist(entity);
            return entity;
        }
    }

    @SneakyThrows
    private <T> Object getValueReplace(T entity, String key, Object value) {
        Class<?> propertyType = PropertyUtils.getPropertyType(entity, key);
        if (propertyType.isInstance(value)) {
            return value;
        }
        boolean persistenceClass = isPersistenceClass(propertyType);
        if (!persistenceClass) {
            return value;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> lookup = (Map<String, Object>) value;
        return findEntityBy(propertyType, lookup);
    }

    private boolean isPersistenceClass(Class<?> propertyType) {
        String propertyClassName = propertyType.getName();
        String packageName = Extraction.class.getPackageName();
        return propertyClassName.startsWith(packageName + ".");
    }

    private String getEntityName(Class<?> entityClasss) {
        return entityManager.getMetamodel()
                .getEntities()
                .stream()
                .filter(entityType ->
                        entityType.getJavaType() == entityClasss)
                .map(EntityType::getName)
                .findFirst()
                .orElseThrow();
    }

    @SneakyThrows
    void setProperty(ExcelTableWithHeader.Cursor cursor,
                     Object target,
                     String targetProperty,
                     Map<String, String> lookupProperties) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, targetProperty);
        Map<String, Object> lookup = getLookup(cursor, lookupProperties);
        Object entity = findEntityBy(propertyType, lookup);
        BeanUtils.setProperty(target, targetProperty, entity);
    }

    @SneakyThrows
    void setProperty(ExcelTableWithHeader.Cursor cursor,
                     Object target,
                     String targetProperty,
                     String columnName) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, targetProperty);
        Object value = valueForType(cursor, propertyType).apply(columnName);
        BeanUtils.setProperty(target, targetProperty, value);
    }

    @SneakyThrows
    void setComponentValue(
            ExcelTableWithHeader.Cursor cursor,
            Object target,
            String targetProperty,
            Map<String, Object> componentLookup,
            String componentProperty,
            String columnName) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, targetProperty);
        if ( Collection.class.isAssignableFrom(propertyType) ) {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(target, targetProperty);
            Method readMethod = propertyDescriptor.getReadMethod();
            Type genericReturnType = readMethod.getGenericReturnType();
            Class<?> componentType = (Class<?>) ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];

            Object componentBean = findEntityBy(componentType, componentLookup);
            setProperty(cursor, componentBean, componentProperty, columnName);

            @SuppressWarnings("unchecked")
            Collection<Object> components = (Collection<Object>) PropertyUtils.getProperty(target, targetProperty);
            components.add(componentBean);
        } else {
            Object componentBean = findEntityBy(
                    propertyType,
                    componentLookup);
            setProperty(cursor, componentBean,
                    componentProperty, columnName);
            BeanUtils.setProperty(target, targetProperty, componentBean);
        }

    }

    private Function<String,?> valueForType(
            ExcelTableWithHeader.Cursor cursor,
            Class<?> propertyType) {
        if (propertyType == String.class) {
            return cursor::getStringValue;
        }
        if (propertyType == Date.class) {
            return cursor::getDateValue;
        }
        if (propertyType == BigDecimal.class) {
            return cursor::getNumericValue;
        }
        return cursor::getStringValue;
    }

    private static Map<String, Object> getLookup(
            ExcelTableWithHeader.Cursor cursor,
            Map<String, String> lookupProperties) {
        // TODO Use stream
        Map<String, Object> lookup = new HashMap<>();
        lookupProperties.forEach((key, value) -> {
            // TODO Use property type
            lookup.put(key, cursor.getStringValue(value));
        });
        return lookup;
    }
}
