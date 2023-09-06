package com.github.zybercik00.repository.process.metadata;

import com.github.zybercik00.domain.metadata.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeEntityRepo extends JpaRepository<AttributeEntity, String> {

    Optional<AttributeEntity> findByPath(String path);
}
