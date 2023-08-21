package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.zybercik00.domain.process.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXTRACTION",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_EXTRACTION",
                columnNames = {"MATERIAL", "PREPARED_ON"}))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Extraction {

    public Extraction() {
        waste = new Waste(this);
        purchasePrices = new ArrayList<>();
        salePrices = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extraction_generator")
    @SequenceGenerator(name = "extraction_generator", sequenceName = "extraction_sec", allocationSize = 50)
    @Column(name = "EXTRACTION_ID")
    private Long id;

    @JoinColumn(name = "MATERIAL",
            foreignKey = @ForeignKey(name = "FK_EXT_MAT"))
    @ManyToOne
    @EqualsAndHashCode.Include
    private Material material;

    @Column(name = "PREPARED_ON")
    @EqualsAndHashCode.Include
    private Date preparedOn;

    @Column(name = "WEIGHT_BEFORE")
    private BigDecimal weightBefore;

    @Column(name = "WEIGHT_AFTER")
    private BigDecimal weightAfter;

    @PrimaryKeyJoinColumn(name = "REALIZED_BY")
    @OneToOne(cascade = CascadeType.ALL)
    private Employee realizedBy;

    @Column(name = "RECEIVED_IN_BERN")
    private Date receivedBackOn;

    @PrimaryKeyJoinColumn(name = "WASTE")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Waste waste;

    @Column(name = "SAMPLE_TEST_RESULT")
    private BigDecimal sampleTestResult;

    @OneToMany(mappedBy = "extraction")
    @JsonManagedReference
    private List<PurchasePrice> purchasePrices;

    @OneToMany(mappedBy = "extraction")
    @JsonManagedReference
    private List<SalePrice> salePrices;
}