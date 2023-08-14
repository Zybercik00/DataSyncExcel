//package com.github.zybercik00;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.zybercik00.domain.metadata.QualifiedAttributeEntity;
//import com.github.zybercik00.domain.metadata.ReferenceAttributeEntity;
//import com.github.zybercik00.domain.metadata.SimpleAttributeEntity;
//import com.github.zybercik00.repository.process.metadata.AttributeEntityRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import java.io.IOException;
//import java.net.URL;
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class MetadataInitializer {
//
//    private final ResourceLoader resourceLoader;
//    private final AttributeEntityRepo attributeEntityRepo;
//
//
//
//    void init() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:metadata/attributes.json");
//        URL url = resource.getURL();
//        Attributes attributes = new ObjectMapper().readValue(url, Attributes.class);
//        for (ReferencedAttributeJson referencedAttributeJson : attributes.getReference()) {
//            ReferenceAttributeEntity referenceAttributeEntity = new ReferenceAttributeEntity();
//            referenceAttributeEntity.setPath(referencedAttributeJson.getPath());
//            referenceAttributeEntity.setTargetProperty(referencedAttributeJson.getTargetProperty());
//            referenceAttributeEntity.setNestedProperty(referencedAttributeJson.getNestedProperty());
//            attributeEntityRepo.save(referenceAttributeEntity);
//        }
//        for (SimpleAttributeJson simpleAttributeJson : attributes.getSimple()) {
//            SimpleAttributeEntity simpleAttributeEntity = new SimpleAttributeEntity();
//            simpleAttributeEntity.setPath(simpleAttributeJson.getPath());
//            simpleAttributeEntity.setTargetProperty(simpleAttributeJson.getTargetProperty());
//            attributeEntityRepo.save(simpleAttributeEntity);
//        }
//        for (QualifiedAttributeJson qualifiedAttributeJson : attributes.getQualified()) {
//            QualifiedAttributeEntity qualifiedAttributeEntity = new QualifiedAttributeEntity();
//            qualifiedAttributeEntity.setPath(qualifiedAttributeJson.getPath());
//            qualifiedAttributeEntity.setTargetProperty(qualifiedAttributeJson.getTargetProperty());
//            qualifiedAttributeEntity.setQualifiedProperty(qualifiedAttributeJson.getQualifierProperty());
//            qualifiedAttributeEntity.setQualifiedParent(qualifiedAttributeJson.getQualifierParent());
//            qualifiedAttributeEntity.setQualifier(qualifiedAttributeJson.getQualifier().toString());
//            attributeEntityRepo.save(qualifiedAttributeEntity);
//        }
//    }
//}
