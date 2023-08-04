package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.repository.metadata.MappingRepo;
import com.github.zybercik00.repository.metadata.AttributeRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
