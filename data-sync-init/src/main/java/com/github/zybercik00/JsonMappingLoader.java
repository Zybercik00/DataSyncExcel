package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.json.AttributeMappings;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
public class JsonMappingLoader {
    private final ObjectMapper objectMapper;
    @Setter
    private Resource resource;
    private URL url;

    void init() throws IOException {
        url = resource.getURL();
    }

    AttributeMappings getAttributeMappings() throws IOException {
        return objectMapper.readValue(url, AttributeMappings.class);
    }

}
