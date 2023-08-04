package com.github.zybercik00.entity.metadata;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "QUALIFIER_VALUE")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class QualifierValueEntity {

    @Id
    @GeneratedValue
    private Long id;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "ATTRIBUTE_ID")
    @ToString.Exclude
    private QualifiedAttributeEntity attribute;

    @Column(name = "QCODE", length = 32)
    @EqualsAndHashCode.Include
    private String code;

    @Column(name = "QVALUE", length = 1024)
    @EqualsAndHashCode.Include
    private String value;
}
