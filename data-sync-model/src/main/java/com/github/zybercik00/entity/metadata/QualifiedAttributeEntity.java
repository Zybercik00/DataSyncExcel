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

    @Column(name = "QUALIFIER_PROPERTY", length = 128, nullable = false)
    @EqualsAndHashCode.Include
    private String qualifierProperty;

    @Column(name = "PARENT_PROPERTY", length = 128, nullable = false)
    @EqualsAndHashCode.Include
    private String qualifierParent;

    @OneToMany(mappedBy = "attribute",
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    private List<QualifierValueEntity> values;
}
