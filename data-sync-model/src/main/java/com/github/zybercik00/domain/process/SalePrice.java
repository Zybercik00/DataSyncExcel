package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PURCHASE_PRICE", uniqueConstraints = @UniqueConstraint(
        name = "UC_SALE_PRICE",
        columnNames = {"MARGIN", "EXTRACTION"}))
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
    private Long id;

    @Column(name = "SALE_PRICE")
    @JsonFormat
    private BigDecimal salePrice;

    @JoinColumn(name = "MARGIN",
            foreignKey = @ForeignKey(name = "FK_SPR_MRG"))
    @ManyToOne
    @EqualsAndHashCode.Include
    private Margin margin;

    @JoinColumn(name = "EXTRACTION",
            foreignKey = @ForeignKey(name = "FK_SPR_EXT"))
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Include
    private Extraction extraction;
}
