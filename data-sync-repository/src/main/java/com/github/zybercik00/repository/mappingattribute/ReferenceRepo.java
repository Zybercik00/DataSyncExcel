package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.entity.metadata.ReferenceAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepo extends JpaRepository<ReferenceAttributeEntity, String> {
}
