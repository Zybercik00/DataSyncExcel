package com.github.zybercik00.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAPPING_ENTITY")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MappingEntity {

    @Id
    @GeneratedValue
    private Long id;

    //excel
    @EqualsAndHashCode.Include
    @Column(name = "SOURCE")
    private String source;

    @EqualsAndHashCode.Include
    @Column(name = "TARGET")
    private AttributeEntity target;

}
