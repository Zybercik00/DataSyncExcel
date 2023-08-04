package com.github.zybercik00;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import com.github.zybercik00.entity.metadata.MappingEntity;
import com.github.zybercik00.json.AttributeMapping;
import com.github.zybercik00.json.AttributeMappings;
import com.github.zybercik00.repository.metadata.AttributeRepo;
import com.github.zybercik00.repository.metadata.MappingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class DataInitializerService {
    private final AttributeUpdater attributeUpdater;
    private final MappingUpdater mappingUpdater;

    void init() throws IOException {
        attributeUpdater.loadAttributes();
        mappingUpdater.loadMappings();
    }


}
