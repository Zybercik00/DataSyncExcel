package com.github.zybercik00.entity.metadata;

import com.fasterxml.jackson.databind.JsonNode;
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
    @JoinColumn(name = "ATTRIBUTE_ID", nullable = false)
    @ToString.Exclude
    private QualifiedAttributeEntity attribute;

    @Column(name = "QCODE", length = 32, nullable = false)
    @EqualsAndHashCode.Include
    private String code;

    @Column(name = "QVALUE", length = 1024, nullable = false)
    @EqualsAndHashCode.Include
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode value;
}
