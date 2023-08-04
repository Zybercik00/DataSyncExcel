package com.github.zybercik00;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import com.github.zybercik00.repository.metadata.AttributeRepo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class AttributeUpdater {
    private final AttributeRepo attributeRepo;
    private final JsonAttributesLoader attributesLoader;
    private final AttributeEntityFactory attributeEntityFactory;

    void loadAttributes() throws IOException {
        List<Object> jsonAttributes = attributesLoader.getJsonAttributes();

        for (Object jsonAttribute : jsonAttributes) {
            AttributeEntity entity = attributeEntityFactory.newAttributeEntity(jsonAttribute);
            String path = entity.getPath();
            AttributeEntity persisted = attributeRepo.findByPath(path);
            if ( persisted != null ) {
                persisted.setTargetProperty(entity.getTargetProperty());
                // TODO Copy other attribute properties
                attributeRepo.save(persisted);
                continue;
            }
            attributeRepo.save(entity);
        }
    }


}
