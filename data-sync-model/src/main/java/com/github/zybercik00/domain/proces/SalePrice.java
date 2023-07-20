package com.github.zybercik00.domain.proces;

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
    private BigDecimal salePrice;

    @JoinColumn(name = "CURRENCY")
    @ManyToOne
    private Currency currency;

    @JoinColumn(name = "MARGIN")
    @ManyToOne
    private Margin margin;

    @Column(name = "PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    @JoinColumn
    @OneToOne(mappedBy = "purchasePrice")
    private Price price;

    @JoinColumn(name = "EXTRACTION")
    @ManyToOne
    private Extraction extraction;
}
