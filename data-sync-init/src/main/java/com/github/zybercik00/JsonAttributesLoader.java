package com.github.zybercik00;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zybercik00.json.AttributeMap;
import com.github.zybercik00.json.Attributes;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JsonAttributesLoader {
    private final ObjectMapper objectMapper;
    @Setter
    private Resource resource;
    private URL url;

    void init() throws IOException {
        url = resource.getURL();
    }


    List<Object> getJsonAttributes() throws IOException {
        Attributes attributes = objectMapper.readValue(url, Attributes.class);
        AttributeMap attributeMap = attributes.getAttributes();
        List<Object> jsonAttributes = new ArrayList<>();
        // References should be mapped first!
        jsonAttributes.addAll(attributeMap.getReference());
        jsonAttributes.addAll(attributeMap.getSimple());
        jsonAttributes.addAll(attributeMap.getQualified());
        return jsonAttributes;
    }

}
