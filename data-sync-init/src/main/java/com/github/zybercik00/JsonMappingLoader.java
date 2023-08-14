package com.github.zybercik00;

import com.github.zybercik00.domain.metadata.QualifiedAttributeEntity;
import com.github.zybercik00.domain.metadata.ReferenceAttributeEntity;
import com.github.zybercik00.domain.metadata.SimpleAttributeEntity;

import java.io.IOException;

public class JsonMappingLoader {

    JsonAttributeLoader jsonAttributeLoader;

    ReferenceAttributeEntity loadReferenced() throws IOException {
        ReferenceAttributeEntity referenceAttributeEntity = new ReferenceAttributeEntity();
        for (ReferencedAttributeJson referencedAttributeJson : jsonAttributeLoader.load().getReference()) {
            referenceAttributeEntity.setPath(referencedAttributeJson.getPath());
            referenceAttributeEntity.setTargetProperty(referencedAttributeJson.getTargetProperty());
            referenceAttributeEntity.setNestedProperty(referencedAttributeJson.getNestedProperty());
        }
        return referenceAttributeEntity;
    }
    SimpleAttributeEntity loadSimple() throws IOException {
        SimpleAttributeEntity simpleAttributeEntity = new SimpleAttributeEntity();
        for (SimpleAttributeJson simpleAttributeJson : jsonAttributeLoader.load().getSimple()) {
            simpleAttributeEntity.setPath(simpleAttributeJson.getPath());
            simpleAttributeEntity.setTargetProperty(simpleAttributeJson.getTargetProperty());
        }
        return simpleAttributeEntity;
    }
    QualifiedAttributeEntity loadQualified() throws IOException {
        QualifiedAttributeEntity qualifiedAttributeEntity = new QualifiedAttributeEntity();
        for (QualifiedAttributeJson qualifiedAttributeJson : jsonAttributeLoader.load().getQualified()) {
            qualifiedAttributeEntity.setPath(qualifiedAttributeJson.getPath());
            qualifiedAttributeEntity.setTargetProperty(qualifiedAttributeJson.getTargetProperty());
            qualifiedAttributeEntity.setQualifiedProperty(qualifiedAttributeJson.getQualifierProperty());
            qualifiedAttributeEntity.setQualifiedParent(qualifiedAttributeJson.getQualifierParent());
            qualifiedAttributeEntity.setQualifier(qualifiedAttributeJson.getQualifier().toString());
        }
        return qualifiedAttributeEntity;
    }
}
