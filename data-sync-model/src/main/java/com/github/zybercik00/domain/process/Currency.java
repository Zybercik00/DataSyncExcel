package com.github.zybercik00.domain.process;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CURRENCY",
        uniqueConstraints = @UniqueConstraint(
            name = "UC_CURRENCY_NME",
            columnNames = "CURRENCY_NAME"))
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

    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }
}
