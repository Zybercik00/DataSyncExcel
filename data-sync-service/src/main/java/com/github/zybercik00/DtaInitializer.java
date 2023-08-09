package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;

public class DtaInitializer {

    private ResourceLoader resourceLoader;



    void iniy() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:metadata/attributes.json");
        URL url = resource.getURL();
        Attributes attributes = new ObjectMapper().readValue(url, Attributes.class);
    }
}
