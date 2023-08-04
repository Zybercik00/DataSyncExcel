package com.github.zybercik00.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
// TODO Performance
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ATTRIBUTE")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AttributeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PATH")
    @EqualsAndHashCode.Include
    private String path;

}
