package com.github.zybercik00.repository.metadata;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import com.github.zybercik00.entity.metadata.MappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingRepo extends JpaRepository<MappingEntity, String> {
    MappingEntity findBySourceAndTarget(String source, AttributeEntity target);
}
