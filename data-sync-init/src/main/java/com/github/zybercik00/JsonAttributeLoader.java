package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
public class JsonAttributeLoader {

    private final ResourceLoader resourceLoader;

    Attributes load() throws IOException  {
        Resource resource = resourceLoader.getResource("classpath:metadata/attributes.json");
        URL url = resource.getURL();
        return new ObjectMapper().readValue(url, Attributes.class);
    }
}
