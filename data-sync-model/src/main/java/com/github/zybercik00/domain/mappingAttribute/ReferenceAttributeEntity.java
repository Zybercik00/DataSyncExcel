package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "REFERENCE_ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
@DiscriminatorValue("2")
public class ReferenceAttributeEntity extends AttributeEntity {


    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;
    @Column(name = "NESTED_PROPERTY")
    private String nestedProperty;
}
