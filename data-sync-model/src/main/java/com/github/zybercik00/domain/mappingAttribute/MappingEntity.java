package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAPPING_ENTITY",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_MAPPING_ENTITY",
                columnNames = {"TARGET", "SOURCE"}))
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MappingEntity {

    @Id
    @GeneratedValue
    private Long id;

    //excel
    @EqualsAndHashCode.Include
    @Column(name = "SOURCE", length = 32, nullable = false)
    private String source;

    //db attribute
    @EqualsAndHashCode.Include
    @JoinColumn(name = "TARGET", nullable = false)
    @ManyToOne
    private AttributeEntity target;

}
