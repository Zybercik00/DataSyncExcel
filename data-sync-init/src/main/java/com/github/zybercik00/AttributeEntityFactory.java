package com.github.zybercik00;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.zybercik00.entity.metadata.*;
import com.github.zybercik00.json.QualifiedAttribute;
import com.github.zybercik00.json.QualifierValue;
import com.github.zybercik00.json.ReferenceAttribute;
import com.github.zybercik00.json.SimpleAttribute;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AttributeEntityFactory {
    private final ObjectMapper objectMapper;

    AttributeEntity newAttributeEntity(Object jsonAttribute) {
        if (jsonAttribute instanceof SimpleAttribute attribute) {
            return getSimpleAttributeEntity(attribute);
        }
        if (jsonAttribute instanceof ReferenceAttribute attribute) {
            return getReferenceAttributeEntity(attribute);
        }
        if (jsonAttribute instanceof QualifiedAttribute attribute) {
            return getQualifiedAttributeEntity(attribute);
        }
        throw new AssertionError();
    }

    private ReferenceAttributeEntity getReferenceAttributeEntity(ReferenceAttribute referenceAttribute) {
        ReferenceAttributeEntity entity = new ReferenceAttributeEntity();
        entity.setNestedProperty(referenceAttribute.getNestedProperty());
        entity.setTargetProperty(referenceAttribute.getTargetProperty());
        entity.setPath(referenceAttribute.getTargetProperty());
        return entity;
    }

    private SimpleAttributeEntity getSimpleAttributeEntity(SimpleAttribute simpleAttribute) {
        SimpleAttributeEntity entity = new SimpleAttributeEntity();
        entity.setTargetProperty(simpleAttribute.getTargetProperty());
        entity.setPath(simpleAttribute.getTargetProperty());
        return entity;
    }

    private QualifiedAttributeEntity getQualifiedAttributeEntity(QualifiedAttribute qualifiedAttribute) {
        QualifiedAttributeEntity entity = new QualifiedAttributeEntity();
        entity.setTargetProperty(qualifiedAttribute.getTargetProperty());
        entity.setQualifierProperty(qualifiedAttribute.getQualifierProperty());
        entity.setQualifierParent(qualifiedAttribute.getQualifierParent());
        List<QualifierValueEntity> values = qualifiedAttribute.getQualifierValue()
                .stream()
                .map(qualifierValue ->
                        getQualifierValueEntity(entity, qualifierValue))
                .collect(Collectors.toList());
        entity.setValues(values);

        String path = getPath(entity);
        entity.setPath(path);
        return entity;
    }

    private String getPath(QualifiedAttributeEntity entity) {
        String qualifierPath = entity.getValues()
                .stream()
                .map(qualifierValue -> {
                    String code = qualifierValue.getCode();
                    JsonNode jsonNode = qualifierValue.getValue();
                    return code + getPath(jsonNode);
                })
                .collect(Collectors.joining(","));
        return entity.getTargetProperty() + "[" +
                entity.getQualifierParent() + "," +
                qualifierPath + "]." +
                entity.getQualifierProperty();
    }

    private String getPath(JsonNode jsonNode) {
        if (jsonNode instanceof TextNode node) {
            return "'" + node.asText() + "'";
        }
        if (jsonNode instanceof ObjectNode node) {
            List<String> pathItems = new ArrayList<>();
            for (Map.Entry<String, JsonNode> property : node.properties()) {
                String key = property.getKey();
                JsonNode value = property.getValue();
                String path = getPath(value);
                pathItems.add(key + "=" + path);
            }
            return "[" + String.join(",", pathItems) + "]";
        }
        throw new IllegalStateException();
    }

    private QualifierValueEntity getQualifierValueEntity(QualifiedAttributeEntity qualifierEntity, QualifierValue qualifierValue) {
        QualifierValueEntity valueEntity = new QualifierValueEntity();
        valueEntity.setAttribute(qualifierEntity);
        valueEntity.setCode(qualifierValue.getKey());

        Object value = qualifierValue.getValue();
        JsonNode jsonNode = valueToTree(value);
        valueEntity.setValue(jsonNode);
        return valueEntity;
    }

    private JsonNode valueToTree(Object value) {
        if (value instanceof String string) {
            return new TextNode(string);
        }
        if (value instanceof List<?> list) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            for (Object element : list) {
                Map<?, ?> map = (Map<?, ?>) element;
                String elementKey = (String) map.get("key");
                Object elementValue = map.get("value");
                objectNode.set(elementKey, valueToTree(elementValue));
            }
            return objectNode;
        }
        throw new IllegalStateException();
    }

}
