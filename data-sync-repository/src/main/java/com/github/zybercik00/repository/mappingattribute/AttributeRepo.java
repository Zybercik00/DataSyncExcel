package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.domain.metadata.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<AttributeEntity, String> {
}
