package com.github.zybercik00.repository.metadata;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<AttributeEntity, String> {
    AttributeEntity findByPath(String path);
}
