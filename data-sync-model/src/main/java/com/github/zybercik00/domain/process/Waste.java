package com.github.zybercik00.domain.process;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "WASTE",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_WASTE",
                columnNames = "EXTRACTION_ID"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Waste {

    public Waste() {
        extraction = new Extraction();
    }

    public Waste(Extraction extraction) {
        this.extraction = extraction;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "waste_generator")
    @SequenceGenerator(name = "waste_generator", sequenceName = "waste_sec", allocationSize = 50)
    @Column(name = "WASTE_ID")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTRACTION_ID",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_WST_EXT"))
    @EqualsAndHashCode.Include
    @JsonIgnore
    private Extraction extraction;
}