package com.github.zybercik00.entity.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import com.github.zybercik00.entity.process.Material;

import java.util.List;

@Entity
@Table(name = "SUPPLIER",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_SUPPLIER",
                columnNames = "SUPPLIER_NAME"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Supplier {

    @Id
    @GeneratedValue
    @Nonnull
    @Column(name = "SUPPLIER_ID")
    private Long id;

    @Nonnull
    @Column(name = "SUPPLIER_NAME")
    @EqualsAndHashCode.Include
    private String name;

    @OneToMany(mappedBy = "supplier")
    private List<Material> supplierMaterials;
}
