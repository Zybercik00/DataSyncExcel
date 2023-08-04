package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "SIMPLE_ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
@DiscriminatorValue("1")
public class SimpleAttributeEntity extends AttributeEntity {


    //db attribute
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;

}
