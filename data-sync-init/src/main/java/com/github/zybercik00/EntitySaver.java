package com.github.zybercik00;

import com.github.zybercik00.domain.metadata.AttributeEntity;
import com.github.zybercik00.domain.metadata.QualifiedAttributeEntity;
import com.github.zybercik00.domain.metadata.ReferenceAttributeEntity;
import com.github.zybercik00.domain.metadata.SimpleAttributeEntity;
import com.github.zybercik00.repository.process.metadata.AttributeEntityRepo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EntitySaver {

    private final JsonMappingLoader jsonMappingLoader;
    private final AttributeEntityRepo attributeEntityRepo;



    void save() throws IOException {
        List<ReferenceAttributeEntity> referencedEntities = jsonMappingLoader.loadReferenced();
        for (ReferenceAttributeEntity referencedEntity : referencedEntities) {
            Optional<AttributeEntity> optionalDbEntity = attributeEntityRepo.findByPath(referencedEntity.getPath());
            if (optionalDbEntity.isEmpty()) {
                // TODO update entity from json
                continue;
            }
            attributeEntityRepo.save(referencedEntity);
        }

        List<SimpleAttributeEntity> simpleEntities = jsonMappingLoader.loadSimple();
        for (SimpleAttributeEntity simpleEntity : simpleEntities) {
            Optional<AttributeEntity> optionalDbEntity = attributeEntityRepo.findByPath(simpleEntity.getPath());
            if (optionalDbEntity.isEmpty()) {
                continue;
            }
            attributeEntityRepo.save(simpleEntity);
        }
        List<QualifiedAttributeEntity> qualifiedEntities = jsonMappingLoader.loadQualified();
        for (QualifiedAttributeEntity qualifiedEntity : qualifiedEntities) {
            Optional<AttributeEntity> optionalDbEntity = attributeEntityRepo.findByPath(qualifiedEntity.getPath());
            if (optionalDbEntity.isEmpty()) {
                continue;
            }
            attributeEntityRepo.save(qualifiedEntity);
        }
    }

}
