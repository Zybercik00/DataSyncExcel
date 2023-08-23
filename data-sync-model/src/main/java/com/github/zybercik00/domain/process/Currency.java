package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CURRENCY",
        uniqueConstraints = @UniqueConstraint(
            name = "UC_CURRENCY_NME",
            columnNames = "CURRENCY_NAME"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_generator")
    @SequenceGenerator(name = "currency_generator", sequenceName = "currency_sec", allocationSize = 50)
    @Column(name = "CURRENCY_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "CURRENCY_NAME")
    private String code;

    @OneToMany(mappedBy = "currency")
    @JsonIgnore
    @ToString.Exclude
    private List<PurchasePrice> purchasePrices;
}
