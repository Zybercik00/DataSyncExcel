package com.github.zybercik00.domain.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WAREHOUSE", uniqueConstraints = @UniqueConstraint(
        name = "UC_WAREHOUSE_NME",
        columnNames = "WAREHOUSE_NAME"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_generator")
    @SequenceGenerator(name = "warehouse_generator", sequenceName = "warehouse_sec", allocationSize = 50)
    @Column(name = "WAREHOUSE_ID")
    private Long id;


    @Column(name = "WAREHOUSE_NAME")
    @EqualsAndHashCode.Include
    private String name;


    @JoinColumn(name = "WAREHOUSE_LOCATION")
    @ManyToOne(cascade = CascadeType.ALL)
    private WarehouseLocation warehouseLocation;
}
