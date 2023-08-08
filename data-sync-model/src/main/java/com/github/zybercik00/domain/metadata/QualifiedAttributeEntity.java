package com.github.zybercik00.domain.metadata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "QUALIFIER_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class QualifiedAttributeEntity extends AttributeEntity {

    @Column(name = "QUALIFIED_PROPERTY")
    private String qualifiedProperty;
    @Column(name = "QUALIFIED_PARENT")
    private String qualifiedParent;
    @Column(name = "QUALIFIER")
    private String qualifier;

}
