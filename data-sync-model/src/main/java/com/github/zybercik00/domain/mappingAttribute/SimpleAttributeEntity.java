package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "SIMPLE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class SimpleAttributeEntity extends AttributeEntity {

}
