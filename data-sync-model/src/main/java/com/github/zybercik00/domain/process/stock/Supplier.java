package com.github.zybercik00.domain.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import com.github.zybercik00.domain.process.Material;

import java.util.List;

@Entity
@Table(name = "SUPPLIER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_generator")
    @SequenceGenerator(name = "supplier_generator", sequenceName = "supplier_sec", allocationSize = 50)
    @Nonnull
    @Column(name = "SUPPILER_ID")
    private Long id;

    @Nonnull
    @Column(name = "SUPPILER_NAME")
    @EqualsAndHashCode.Include
    private String name;

    @Nonnull
    @Column(name = "MATERIALS")
    @OneToMany
    private List<Material> supplierMaterials;
}
