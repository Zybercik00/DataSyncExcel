package com.github.zybercik00;

import com.github.zybercik00.domain.metadata.QualifiedAttributeEntity;
import com.github.zybercik00.domain.metadata.ReferenceAttributeEntity;
import com.github.zybercik00.domain.metadata.SimpleAttributeEntity;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JsonMappingLoader {

    private final JsonAttributeLoader jsonAttributeLoader;

    List<ReferenceAttributeEntity> loadReferenced() throws IOException {
        List<ReferenceAttributeEntity> referenceAttributeEntityList = new ArrayList<>();
        for (ReferencedAttributeJson referencedAttributeJson : jsonAttributeLoader.load().getReference()) {
            ReferenceAttributeEntity referenceAttributeEntity = new ReferenceAttributeEntity();
            referenceAttributeEntity.setPath(referencedAttributeJson.getPath());
            referenceAttributeEntity.setTargetProperty(referencedAttributeJson.getTargetProperty());
            referenceAttributeEntity.setNestedProperty(referencedAttributeJson.getNestedProperty());
            referenceAttributeEntityList.add(referenceAttributeEntity);
        }
        return referenceAttributeEntityList;
    }
    List<SimpleAttributeEntity> loadSimple() throws IOException {
        List<SimpleAttributeEntity> simpleAttributeEntityList = new ArrayList<>();
        for (SimpleAttributeJson simpleAttributeJson :  jsonAttributeLoader.load().getSimple()) {
            SimpleAttributeEntity simpleAttributeEntity = new SimpleAttributeEntity();
            simpleAttributeEntity.setPath(simpleAttributeJson.getPath());
            simpleAttributeEntity.setTargetProperty(simpleAttributeJson.getTargetProperty());
            simpleAttributeEntityList.add(simpleAttributeEntity);
        }
        return simpleAttributeEntityList;
    }
    List<QualifiedAttributeEntity> loadQualified() throws IOException {
        List<QualifiedAttributeEntity> qualifiedAttributeEntityList = new ArrayList<>();
        for (QualifiedAttributeJson qualifiedAttributeJson : jsonAttributeLoader.load().getQualified()) {
            QualifiedAttributeEntity qualifiedAttributeEntity = new QualifiedAttributeEntity();
            qualifiedAttributeEntity.setPath(qualifiedAttributeJson.getPath());
            qualifiedAttributeEntity.setTargetProperty(qualifiedAttributeJson.getTargetProperty());
            qualifiedAttributeEntity.setQualifiedProperty(qualifiedAttributeJson.getQualifierProperty());
            qualifiedAttributeEntity.setQualifiedParent(qualifiedAttributeJson.getQualifierParent());
            qualifiedAttributeEntity.setQualifier(qualifiedAttributeJson.getQualifier().toString());
            qualifiedAttributeEntityList.add(qualifiedAttributeEntity);
        }
        return qualifiedAttributeEntityList;
    }
}
