package com.github.zybercik00.entity.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REFERENCE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class ReferenceAttributeEntity extends AttributeEntity {

    @Column(name = "NESTED_PROPERTY", length = 32, nullable = false)
    private String nestedProperty;
}
