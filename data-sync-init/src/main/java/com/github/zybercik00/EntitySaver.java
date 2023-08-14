package com.github.zybercik00;

import com.github.zybercik00.repository.process.metadata.AttributeEntityRepo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class EntitySaver {

    private final AttributeEntityRepo attributeEntityRepo;



    void save() throws IOException {
        JsonMappingLoader mappingLoader = new JsonMappingLoader();
        attributeEntityRepo.save(mappingLoader.loadReferenced());
        attributeEntityRepo.save(mappingLoader.loadSimple());
        attributeEntityRepo.save(mappingLoader.loadQualified());
    }


}
