package com.github.zybercik00.entity.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WAREHOUSE_LOCATION",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_WAREHOUSE_LOCATION",
                columnNames = "LOCATION_NAME"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class WarehouseLocation {

    @Id
    @GeneratedValue
    @Nonnull
    @Column(name = "WAREHOUSE_LOCATION_ID")
    private Long id;

    @Nonnull
    @Column(name = "LOCATION_NAME")
    @EqualsAndHashCode.Include
    private String locationName;
}
