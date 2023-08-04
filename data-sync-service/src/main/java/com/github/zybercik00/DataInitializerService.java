package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.domain.mappingAttribute.*;
import com.github.zybercik00.repository.mappingattribute.SimpleAttributeEntityRepo;
import com.github.zybercik00.repository.mappingattribute.QualifierAttributeEntityRepo;
import com.github.zybercik00.repository.mappingattribute.ReferenceAttributeEntityRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Service
@RequiredArgsConstructor
public class DataInitializerService {

    private final SimpleAttributeEntityRepo simpleAttributeEntityRepo;
    private final ReferenceAttributeEntityRepo referenceAttributeEntityRepo;
    private final QualifierAttributeEntityRepo qualifierAttributeEntityRepo;
    private final ObjectMapper objectMapper;


    @PostConstruct
    public void initializeData() {

// TODO get target as AttributeEntity object

        simpleAttributeEntityRepo.save(MappingEntity.builder()
                .target()
                .source("Name")
                .build());
        simpleAttributeEntityRepo.save(MappingEntity.builder()
                .target("preparedOn")
                .source("Made on")
                .build());
        simpleAttributeEntityRepo.save(MappingEntity.builder()
                .target("weightBefore")
                .source("Weight before")
                .build());

        SimpleAttributeEntity.builder()
                .targetProperty("material.name")
                .path("material.name")
                .build();
        // TODO Save

    }
}
