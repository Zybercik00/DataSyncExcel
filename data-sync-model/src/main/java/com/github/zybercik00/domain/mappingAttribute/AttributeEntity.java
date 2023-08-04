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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ATTRIBUTE_TYPE", discriminatorType = DiscriminatorType.INTEGER)
public class AttributeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "PATH")
    @EqualsAndHashCode.Include
    private String path;

}
