package com.github.zybercik00.entity.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "WASTE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Waste {

    @Id
    @GeneratedValue
    @Column(name = "WASTE_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PACKED_KG")
    private BigDecimal packedKg;

    @Column(name = "LOSS_AFTER_EXTRACTION_KG")
    private BigDecimal lossAfterExtractionInKg;

    @Column(name = "LOSS_AFTER_EXTRACTION_PERCENT")
    private BigDecimal lossAfterExtractionInPercent;

    @Column(name = "LOSS_TOTAL_KG")
    private BigDecimal lossTotalKg;

    @Column(name = "LOSS_TOTAL_PERCENTS")
    private BigDecimal lossTotalPercents;

    @OneToOne(mappedBy = "waste")
    @JsonIgnore
    private Extraction extraction;
}