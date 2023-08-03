package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.domain.mappingAttribute.QualifierAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualifierMappingAttributeRepo extends JpaRepository<QualifierAttributeEntity, String> {
}
