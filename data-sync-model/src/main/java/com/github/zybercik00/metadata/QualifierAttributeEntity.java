package com.github.zybercik00.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "QUALIFIER_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class QualifierAttributeEntity extends AttributeEntity {

    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;

    @Column(name = "QUALIFIER")
    private String qualifier;

    @Column(name = "QUALIFIER_PROPERTY")
    private String qualifierProperty;

    @Column(name = "QUALIFIER_PARENT")
    private String qualifierParent;
}
