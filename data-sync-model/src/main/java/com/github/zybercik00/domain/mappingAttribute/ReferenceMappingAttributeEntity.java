package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REFERENCE_MAPPING_ATTRIBUTE_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReferenceMappingAttributeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "PATH")
    private String path;
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;
    @Column(name = "NESTED_PROPERTY")
    private String nestedProperty;
}
