package com.github.zybercik00.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REFERENCE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class ReferenceAttributeEntity extends AttributeEntity {

    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;

    @Column(name = "NESTED_PROPERTY")
    private String nestedProperty;
}
