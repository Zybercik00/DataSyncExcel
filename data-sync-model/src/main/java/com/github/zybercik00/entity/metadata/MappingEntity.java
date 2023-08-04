package com.github.zybercik00.entity.metadata;

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
    @Column(name = "SOURCE", length = 32)
    private String source;

    @EqualsAndHashCode.Include
    @JoinColumn(name = "TARGET")
    @ManyToOne
    private AttributeEntity target;

}
