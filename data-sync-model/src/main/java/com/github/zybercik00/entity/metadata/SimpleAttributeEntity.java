package com.github.zybercik00.entity.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SIMPLE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString
public class SimpleAttributeEntity extends AttributeEntity {

}