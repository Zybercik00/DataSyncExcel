package com.github.zybercik00.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SIMPLE_ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SimpleAttributeEntity extends AttributeEntity {

    //db attribute
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;

}
