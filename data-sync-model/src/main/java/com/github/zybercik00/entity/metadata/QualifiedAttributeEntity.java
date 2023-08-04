package com.github.zybercik00.entity.metadata;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "QUALIFIER_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class QualifiedAttributeEntity extends AttributeEntity {

    @EqualsAndHashCode.Include
    @Column(name = "TARGET_PROPERTY", length = 32)
    private String targetProperty;

    @Column(name = "PROPERTY", length = 32)
    @EqualsAndHashCode.Include
    private String qualifierProperty;

    @OneToMany(mappedBy = "attribute", cascade = {CascadeType.PERSIST})
    private List<QualifierValueEntity> values;
}
