package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PURCHASE_PRICE", uniqueConstraints = @UniqueConstraint(
        name = "UC_PURCHASE_PRICE",
        columnNames = {"CURRENCY", "EXTRACTION"}))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class PurchasePrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchasePrice_generator")
    @SequenceGenerator(name = "purchasePrice_generator", sequenceName = "purchasePrice_sec", allocationSize = 50)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "CURRENCY",
            foreignKey = @ForeignKey(name = "FK_PPR_CUR"))
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    private Currency currency;

    @Column(name = "PURCHASE_PRICE")
    @JsonFormat
    private BigDecimal purchasePrice;


    @JoinColumn(name = "EXTRACTION",
            foreignKey = @ForeignKey(name = "FK_PPR_EXT"))
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Include
    private Extraction extraction;
}
