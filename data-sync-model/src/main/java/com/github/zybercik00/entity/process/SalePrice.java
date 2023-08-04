package com.github.zybercik00.entity.process;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @JoinColumn(name = "MARGIN")
    @ManyToOne
    private Margin margin;

    @JoinColumn(name = "EXTRACTION")
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Extraction extraction;
}
