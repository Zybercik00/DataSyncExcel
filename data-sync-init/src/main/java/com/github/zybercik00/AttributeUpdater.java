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
            String path = attributeEntityFactory.getPath(jsonAttribute);
            AttributeEntity persisted = attributeRepo.findByPath(path);
            if ( persisted != null ) {
                // TODO Update
                continue;
            }
            AttributeEntity entity = attributeEntityFactory.newAttributeEntity(jsonAttribute);
            attributeRepo.save(entity);
        }
    }


}
