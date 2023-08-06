package com.github.zybercik00.entity.metadata;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "QUALIFIER_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class QualifierAttributeEntity extends AttributeEntity {


    @Column(name = "QUALIFIER_PROPERTY", length = 128, nullable = false)
    private String qualifierProperty;

    @Column(name = "QUALIFIER_PARENT", length = 128, nullable = false)
    private String qualifierParent;

    @OneToMany(mappedBy = "attribute", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<QualifierValueEntity> values;
}
