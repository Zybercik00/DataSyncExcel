package com.github.zybercik00.domain.proces;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PURCHASE_PRICE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SalePrice {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "SALE_PRICE")
    @JsonBackReference
    private BigDecimal salePrice;

    @JoinColumn(name = "CURRENCY")
    @ManyToOne
    @JsonBackReference
    private Currency currency;

    @JoinColumn(name = "MARGIN")
    @ManyToOne
    @JsonBackReference
    private Margin margin;

    @Column(name = "PURCHASE_PRICE")
    @JsonBackReference
    private BigDecimal purchasePrice;

    @JoinColumn
    @OneToOne(mappedBy = "purchasePrice")
    @JsonBackReference
    private Price price;

    @JoinColumn(name = "EXTRACTION")
    @ManyToOne
    @JsonBackReference
    private Extraction extraction;
}
