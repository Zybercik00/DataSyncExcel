package com.github.zybercik00.domain.proces;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRICE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Price {

    @Id
    @GeneratedValue
    @Column(name = "PRICE_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @PrimaryKeyJoinColumn(name = "PURCHASE_PRICE")
    @OneToOne
    private PurchasePrice purchasePrice;

    @PrimaryKeyJoinColumn(name = "SALE_PRICE")
    @OneToOne
    private SalePrice salePrice;
}
