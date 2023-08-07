package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonFormat
    private BigDecimal salePrice;

    @JoinColumn(name = "CURRENCY")
    @ManyToOne
    private Currency currency;

    @JoinColumn(name = "MARGIN")
    @ManyToOne
    private Margin margin;

    @Column(name = "PURCHASE_PRICE")
    @JsonFormat
    private BigDecimal purchasePrice;

    @JoinColumn
    @OneToOne(mappedBy = "purchasePrice")
    @JsonIgnore
    private Price price;

    @JoinColumn(name = "EXTRACTION")
    @ManyToOne
    @JsonIgnore
    private Extraction extraction;
}
