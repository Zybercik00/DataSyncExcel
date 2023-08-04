package com.github.zybercik00.entity.metadata;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAPPING_ENTITY",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_MAPPING_ENTITY",
                columnNames = {"SOURCE", "TARGET"}))
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
    @Column(name = "SOURCE", length = 32, nullable = false)
    private String source;

    @EqualsAndHashCode.Include
    @JoinColumn(name = "TARGET", nullable = false)
    @ManyToOne
    private AttributeEntity target;

}
