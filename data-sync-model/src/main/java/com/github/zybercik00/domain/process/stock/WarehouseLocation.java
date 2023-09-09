package com.github.zybercik00.domain.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WAREHOUSE_LOCATION", uniqueConstraints = @UniqueConstraint(
        name = "UC_LOCATION_NME",
        columnNames = "LOCATION_NAME"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class WarehouseLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseLocation_generator")
    @SequenceGenerator(name = "warehouseLocation_generator", sequenceName = "warehouseLocation_sec", allocationSize = 50)
    @Column(name = "WAREHOUSE_LOCATION_ID")
    private Long id;


    @Column(name = "LOCATION_NAME")
    @EqualsAndHashCode.Include
    private String locationName;
}
