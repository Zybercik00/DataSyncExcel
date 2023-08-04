package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.domain.mappingAttribute.MappingEntity;
import com.github.zybercik00.domain.mappingAttribute.QualifierAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.ReferenceAttributeEntity;
import com.github.zybercik00.domain.mappingAttribute.SimpleAttributeEntity;
import com.github.zybercik00.repository.mappingattribute.MappingRepo;
import com.github.zybercik00.repository.mappingattribute.AttributeRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataInitializerService {

    private final MappingRepo mappingRepo;
    private final AttributeRepo attributeRepo;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void initializeData() {
    }
}
