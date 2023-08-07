package com.github.zybercik00.domain.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WAREHOUSE_LOCATION")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
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
