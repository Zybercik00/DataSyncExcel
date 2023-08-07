package com.github.zybercik00.domain.metadata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "REFERENCE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class ReferenceAttributeEntity extends AttributeEntity {

    @Column(name = "NESTED_PROPERTY")
    private String nestedProperty;
}
