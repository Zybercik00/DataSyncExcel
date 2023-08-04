package com.github.zybercik00.entity.process;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRICE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Price {

    @Id
    @GeneratedValue
    @Column(name = "PRICE_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @PrimaryKeyJoinColumn(name = "PURCHASE_PRICE")
    @OneToOne
    @JsonBackReference
    private PurchasePrice purchasePrice;

    @PrimaryKeyJoinColumn(name = "SALE_PRICE")
    @OneToOne
    @JsonBackReference
    private SalePrice salePrice;
}
