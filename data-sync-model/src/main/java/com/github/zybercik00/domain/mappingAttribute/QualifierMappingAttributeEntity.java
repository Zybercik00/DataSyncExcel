package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "QUALIFIER_MAPPING_ATTRIBUTE_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class QualifierMappingAttributeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "PATH")
    private String path;
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;
    @Column(name = "QUALIFIER")
    private String qualifier;
    @Column(name = "QUALIFIER_PROPERTY")
    private String qualifierProperty;
    @Column(name = "QUALIFIER_PARENT")
    private String qualifierParent;
}
