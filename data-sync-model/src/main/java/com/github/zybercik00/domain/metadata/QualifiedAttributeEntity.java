package com.github.zybercik00.domain.metadata;

import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


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
