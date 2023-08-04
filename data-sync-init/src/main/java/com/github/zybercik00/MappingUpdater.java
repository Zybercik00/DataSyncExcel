package com.github.zybercik00;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import com.github.zybercik00.entity.metadata.MappingEntity;
import com.github.zybercik00.json.AttributeMapping;
import com.github.zybercik00.json.AttributeMappings;
import com.github.zybercik00.repository.metadata.AttributeRepo;
import com.github.zybercik00.repository.metadata.MappingRepo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class MappingUpdater {
    private final AttributeRepo attributeRepo;
    private final MappingRepo mappingRepo;
    private final JsonMappingLoader jsonMappingLoader;

    void loadMappings() throws IOException {
        AttributeMappings mappings = jsonMappingLoader.getAttributeMappings();
        for (AttributeMapping mapping : mappings.getMappings()) {
            String source = mapping.getSource();
            AttributeEntity target = attributeRepo.findByPath(mapping.getTarget());
            if ( target == null ) {
                throw new IllegalStateException("Missing attribute for mapping: " +
                        "source = " + mapping.getSource() + ", " +
                        "target = " + mapping.getTarget());
            }
            MappingEntity persisted = mappingRepo.findBySourceAndTarget(source, target);
            if ( persisted != null ) {
                continue;
            }
            MappingEntity mappingEntity = new MappingEntity();
            mappingEntity.setSource(source);
            mappingEntity.setTarget(target);
            mappingRepo.save(mappingEntity);
        }
    }
}
