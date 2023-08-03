package com.github.zybercik00.domain.mappingAttribute;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SIMPLE_ATTRIBUTE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SimpleAttributeEntity {

    @Id
    @GeneratedValue
    private Long id;
    //db attribute
    @Column(name = "TARGET_PROPERTY")
    private String targetProperty;

}
