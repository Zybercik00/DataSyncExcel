package com.github.zybercik00.domain.process;

import jakarta.persistence.*;
import lombok.*;
import com.github.zybercik00.domain.process.stock.Supplier;
import com.github.zybercik00.domain.process.stock.Warehouse;

@Entity
@Table(name = "MATERIAL",
        uniqueConstraints = @UniqueConstraint(
            name = "UC_MATERIAL_NME",
            columnNames = "LOT"))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_generator")
    @SequenceGenerator(name = "material_generator", sequenceName = "material_sec", allocationSize = 50)
    @Column(name = "MATERIAL_ID")
    private Long id;


    @Column(name = "LOT")
    @EqualsAndHashCode.Include
    private String lot;


    @Column(name = "MATERIAL_NAME")
    private String name;


    @Column(name = "STATUS_INVENTORY")
    private String inventoryStatus;


    @Column(name = "WEIGHT")
    private String weight;

    @Column
    private String content;


    @JoinColumn(name = "WAREHOUSE",
            foreignKey = @ForeignKey(name = "FK_MAT_WHS"))
    @ManyToOne(cascade = CascadeType.ALL)
    private Warehouse warehouse;


    @JoinColumn(name = "SUPPLIER",
            foreignKey = @ForeignKey(name = "FK_MAT_SPL"))
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;

    public Material() {
    }

    public Material(String lot) {
        this.lot = lot;
    }
}
