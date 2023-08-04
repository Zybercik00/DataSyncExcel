package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAPPING_ENTITY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MappingEntity {

    @Id
    @GeneratedValue
    private Long id;

    //excel
    @Column(name = "SOURCE")
    private String source;

    @Column(name = "TARGET")
    private AttributeEntity target;

}
