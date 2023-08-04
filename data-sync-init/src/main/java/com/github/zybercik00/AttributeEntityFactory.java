package com.github.zybercik00;

import com.github.zybercik00.entity.metadata.*;
import com.github.zybercik00.json.*;

import java.util.List;
import java.util.stream.Collectors;

public class AttributeEntityFactory {

    AttributeEntity newAttributeEntity(Object jsonAttribute) {
        if ( jsonAttribute instanceof SimpleAttribute attribute ) {
            return getSimpleAttributeEntity(attribute);
        }
        if ( jsonAttribute instanceof ReferenceAttribute attribute ) {
            return getReferenceAttributeEntity(attribute);
        }
        if ( jsonAttribute instanceof QualifiedAttribute attribute ) {
            return getQualifiedAttributeEntity(attribute);
        }
        throw new AssertionError();
    }

    String getPath(Object jsonAttribute) {
        if ( jsonAttribute instanceof SimpleAttribute attribute ) {
            return attribute.getPath();
        }
        if ( jsonAttribute instanceof ReferenceAttribute attribute ) {
            return attribute.getPath();
        }
        if ( jsonAttribute instanceof QualifiedAttribute attribute ) {
            return attribute.getPath();
        }
        throw new AssertionError();
    }

    private ReferenceAttributeEntity getReferenceAttributeEntity(ReferenceAttribute referenceAttribute) {
        ReferenceAttributeEntity entity = new ReferenceAttributeEntity();
        entity.setNestedProperty(referenceAttribute.getNestedProperty());
        entity.setTargetProperty(referenceAttribute.getTargetProperty());
        entity.setPath(referenceAttribute.getPath());
        return entity;
    }

    private SimpleAttributeEntity getSimpleAttributeEntity(SimpleAttribute simpleAttribute) {
        SimpleAttributeEntity entity = new SimpleAttributeEntity();
        entity.setTargetProperty(simpleAttribute.getTargetProperty());
        entity.setPath(simpleAttribute.getPath());
        return entity;
    }

    private QualifiedAttributeEntity getQualifiedAttributeEntity(QualifiedAttribute qualifiedAttribute) {
        QualifiedAttributeEntity entity = new QualifiedAttributeEntity();
        entity.setTargetProperty(qualifiedAttribute.getTargetProperty());
        entity.setPath(qualifiedAttribute.getPath());
        entity.setQualifierProperty(qualifiedAttribute.getQualifierProperty());
        List<QualifierValueEntity> values = qualifiedAttribute.getQualifierValue()
                .stream()
                .map(
                        qualifierValue ->
                                getQualifierValueEntity(entity, qualifierValue)
                )
                .collect(Collectors.toList());
        entity.setValues(values);
        return entity;
    }

    private QualifierValueEntity getQualifierValueEntity(QualifiedAttributeEntity qualifierEntity, QualifierValue qualifierValue) {
        QualifierValueEntity valueEntity = new QualifierValueEntity();
        valueEntity.setAttribute(qualifierEntity);
        valueEntity.setCode(qualifierValue.getKey());
        valueEntity.setValue(qualifierValue.getValue());
        return valueEntity;
    }
}
