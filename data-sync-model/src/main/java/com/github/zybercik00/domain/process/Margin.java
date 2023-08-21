package com.github.zybercik00.domain.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "MARGIN", uniqueConstraints = @UniqueConstraint(
        name = "UC_MARGIN_NME",
        columnNames = "MARGIN_NAME"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Margin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "margin_generator")
    @SequenceGenerator(name = "margin_generator", sequenceName = "margin_sec", allocationSize = 50)
    @Column(name = "MARGIN_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "MARGIN_NAME")
    private String name;

    @OneToMany(mappedBy = "margin")
    @JsonIgnore
    private List<SalePrice> prices;
}
