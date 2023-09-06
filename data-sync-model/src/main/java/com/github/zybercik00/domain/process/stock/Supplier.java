package com.github.zybercik00.domain.process.stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import com.github.zybercik00.domain.process.Material;

import java.util.List;

@Entity
@Table(name = "SUPPLIER",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_SUPPLIER_NME",
                columnNames = "SUPPILER_NAME"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_generator")
    @SequenceGenerator(name = "supplier_generator", sequenceName = "supplier_sec", allocationSize = 50)
    @Column(name = "SUPPILER_ID")
    private Long id;


    @Column(name = "SUPPILER_NAME")
    @EqualsAndHashCode.Include
    private String name;


    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Material> supplierMaterials;
}
