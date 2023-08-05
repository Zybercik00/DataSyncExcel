package com.github.zybercik00.entity.process;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXTRACTION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Extraction {

    @Id
    @GeneratedValue
    @Column(name = "EXTRACTION_ID")
    @EqualsAndHashCode.Include
    private Long id;


    @JoinColumn(name = "MATERIAL",
            foreignKey = @ForeignKey(name = "FK_EXT_MAT"))
    @ManyToOne
    private Material material;


    @Column(name = "PREPARED_ON")
    private Date preparedOn;


    @Column(name = "WEIGHT_BEFORE")
    private BigDecimal weightBefore;


    @Column(name = "WEIGHT_AFTER")
    private BigDecimal weightAfter;

    @PrimaryKeyJoinColumn(name = "REALIZED_BY")
    @OneToOne
    private Employee realizedBy;


    @Column(name = "RECEIVED_IN_BERN")
    private Date receivedBackOn;

    @PrimaryKeyJoinColumn(name = "WASTE")
    @OneToOne(cascade = CascadeType.ALL)
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