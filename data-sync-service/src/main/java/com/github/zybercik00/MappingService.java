package com.github.zybercik00;

import com.github.zybercik00.entity.process.Extraction;
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
import java.util.*;
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
                "select " + "e" + " from " + entityName + " " + "e" + " " +
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
                .orElseThrow(() -> new NoSuchElementException(entityClasss.getName()));
    }

    void setValue(ExcelTableWithHeader.Cursor cursor,
                  Object target,
                  String source,
                  MappingAttribute attribute) {
        if ( attribute instanceof SimpleMappingAttribute simpleMappingAttribute) {
            setSimpleValue(cursor, target, simpleMappingAttribute, source);
        } else if (attribute instanceof ReferenceMappingAttribute referenceMappingAttribute ) {
            setReferenceValue(cursor, target, referenceMappingAttribute, source);
        } else if ( attribute instanceof QualifierMappingAttribute qualifierMappingAttribute ) {
            setQualifiedValue(cursor, target, qualifierMappingAttribute, source);
        }
    }


    @SneakyThrows
    private void setReferenceValue(ExcelTableWithHeader.Cursor cursor,
                                   Object target,
                                   ReferenceMappingAttribute attribute,
                                   String columnName) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, attribute.getTargetProperty());
        Map<String, Object> lookup = getLookup(cursor, Map.of(attribute.getNestedProperty(), columnName));
        Object entity = findEntityBy(propertyType, lookup);
        BeanUtils.setProperty(target, attribute.getTargetProperty(), entity);
    }

    @SneakyThrows
    private void setSimpleValue(ExcelTableWithHeader.Cursor cursor,
                                Object target,
                                SimpleMappingAttribute attribute,
                                String columnName) {
        String targetProperty = attribute.getTargetProperty();
        setSimpleValue(cursor, target, columnName, targetProperty);
    }

    @SneakyThrows
    private void setSimpleValue(ExcelTableWithHeader.Cursor cursor,
                                Object target,
                                String columnName,
                                String targetProperty) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, targetProperty);
        // TODO Handle missing value
        Object value = valueForType(cursor, propertyType).apply(columnName);
        BeanUtils.setProperty(target, targetProperty, value);
    }

    @SneakyThrows
    private void setQualifiedValue(
            ExcelTableWithHeader.Cursor cursor,
            Object target,
            QualifierMappingAttribute attribute,
            String columnName) {
        Class<?> propertyType = PropertyUtils.getPropertyType(target, attribute.getTargetProperty());
        if (Collection.class.isAssignableFrom(propertyType)) {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(target, attribute.getTargetProperty());
            Method readMethod = propertyDescriptor.getReadMethod();
            Type genericReturnType = readMethod.getGenericReturnType();
            Class<?> componentType = (Class<?>) ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];

            Map<String, Object> qualifierLookup = new LinkedHashMap<>();
            qualifierLookup.put(attribute.getQualifierParent(), target);
            qualifierLookup.putAll(attribute.getQualifier());
            Object componentBean = findEntityBy(componentType, qualifierLookup);
            setSimpleValue(cursor, componentBean, columnName, attribute.getQualifierProperty());

            @SuppressWarnings("unchecked")
            Collection<Object> components = (Collection<Object>) PropertyUtils.getProperty(target, attribute.getTargetProperty());
            components.add(componentBean);
        } else {
            Object componentBean = findEntityBy(
                    propertyType,
                    attribute.getQualifier());
            setSimpleValue(cursor, componentBean,
                    attribute.getQualifierProperty(), columnName);
            BeanUtils.setProperty(target, attribute.getTargetProperty(), componentBean);
        }

    }


    private Function<String, ?> valueForType(
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

    private Map<String, Object> getLookup(
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