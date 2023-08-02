package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.domain.mappingAttribute.ReferenceMappingAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceMappingAttributeRepo extends JpaRepository<ReferenceMappingAttributeEntity, String> {
}
