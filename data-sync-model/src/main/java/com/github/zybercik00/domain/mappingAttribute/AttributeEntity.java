package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SIMPLE_ATTRIBUTE",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_ATTRIBUTE",
                columnNames = "PATH"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AttributeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PATH", length = 1024, nullable = false)
    @EqualsAndHashCode.Include
    private String path;

    @Column(name = "TARGET_PROPERTY", length = 128, nullable = false)
    private String targetProperty;

}
