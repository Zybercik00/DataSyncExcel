package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REFERENCE_ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReferenceAttributeEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;
    @Column(name = "NESTED_PROPERTY")
    private String nestedProperty;
}
