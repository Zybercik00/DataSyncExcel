package com.github.zybercik00;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class DataInitializerService {
    private final AttributeUpdater attributeUpdater;
    private final MappingUpdater mappingUpdater;

    @Transactional
    void init() throws IOException {
        attributeUpdater.loadAttributes();
        mappingUpdater.loadMappings();
    }


}
