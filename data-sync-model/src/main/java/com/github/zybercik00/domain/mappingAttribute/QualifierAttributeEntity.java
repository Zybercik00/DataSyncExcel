package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "QUALIFIER_ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
@DiscriminatorValue("3")
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
