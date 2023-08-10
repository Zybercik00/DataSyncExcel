package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.domain.metadata.AttributeEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MetadataInitializer {

    private ResourceLoader resourceLoader;
    private AttributeEntity attributeEntity;



    void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:metadata/attributes.json");
        URL url = resource.getURL();
        Attributes attributes = new ObjectMapper().readValue(url, Attributes.class);
    }
}
