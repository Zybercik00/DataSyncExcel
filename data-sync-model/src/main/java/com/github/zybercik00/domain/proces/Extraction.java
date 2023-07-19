package com.github.zybercik00.domain.proces;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXTRACTION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Extraction {

    @Id
    @GeneratedValue
    @Column(name = "EXTRACTION_|D")
    @EqualsAndHashCode.Include
    private Long id;


    @JoinColumn(name = "MATERIAL")
    @ManyToOne
    private Material material;


    @Column(name = "PREPARED_ON")
    private Date preparedOn;


    @Column(name = "HOW_MANY_BAGS")
    private Double howManyBags;

    @Column(name = "WEIGHT_BEFORE")
    private Double weightBefore;


    @Column(name = "NOTIFIED_IN_BINDER")
    private String notifiedInBinder;

    @Column(name = "WEIGHT_AFTER")
    private Double weightAfter;

    @PrimaryKeyJoinColumn(name = "REALIZED_BY")
    @OneToOne
    private Employee realizedBy;


    @Column(name = "RECEIVED_IN_BERN")
    private Date receivedInBernOn;


    @PrimaryKeyJoinColumn(name = "WHO_BROUGHT_MATERIAL")
    @OneToOne
    private Employee whoBroughtMaterial;

    @PrimaryKeyJoinColumn(name = "WASTE")
    @OneToOne
    private Waste waste;


    @Column(name = "THC_BEFORE")
    private double thcBefore;


    @Column(name = "THC_AFTER")
    private double thcAfter;

    @PrimaryKeyJoinColumn(name = "PURCHASE_PRICE")
    @OneToOne
    private PurchasePrice purchasePrice;

    @PrimaryKeyJoinColumn(name = "SALE_PRICE")
    @OneToMany
    private List<SalePrice> salePrice;
}
