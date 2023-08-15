package com.github.zybercik00;

import com.github.zybercik00.repository.process.metadata.AttributeEntityRepo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class EntitySaver {

    private final JsonMappingLoader jsonMappingLoader;
    private final AttributeEntityRepo attributeEntityRepo;



    void save() throws IOException {
        attributeEntityRepo.save(jsonMappingLoader.loadReferenced());
        attributeEntityRepo.save(jsonMappingLoader.loadSimple());
        attributeEntityRepo.save(jsonMappingLoader.loadQualified());
    }


}
