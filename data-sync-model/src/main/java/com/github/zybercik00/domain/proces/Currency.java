package com.github.zybercik00.domain.proces;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CURRENCY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Currency {

    @Id
    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "CURRENCY_NAME")
    private String code;

    @OneToMany(mappedBy = "currency")
    @JsonManagedReference
    private List<PurchasePrice> purchasePrices;
}
