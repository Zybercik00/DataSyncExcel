package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.entity.metadata.AttributeEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface AttributeRepo extends JpaRepository<AttributeEntity, String> {
}
