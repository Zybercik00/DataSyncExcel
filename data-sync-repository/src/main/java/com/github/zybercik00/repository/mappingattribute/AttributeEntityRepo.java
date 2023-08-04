package com.github.zybercik00.repository.mappingattribute;

import com.github.zybercik00.domain.mappingAttribute.AttributeEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface AttributeEntityRepo extends CrudRepository<AttributeEntity, Long> {
}
