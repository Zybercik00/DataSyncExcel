package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.domain.mappingAttribute.MappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingAttributeRepo extends JpaRepository<MappingEntity, String> {
}
