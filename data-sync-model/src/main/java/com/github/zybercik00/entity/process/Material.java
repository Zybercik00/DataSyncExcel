package com.github.zybercik00.entity.process;

import com.github.zybercik00.entity.process.stock.Supplier;
import com.github.zybercik00.entity.process.stock.Warehouse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MATERIAL",
        uniqueConstraints = @UniqueConstraint(
                name = "UC_MATERIAL_NME",
                columnNames = "LOT"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Material {

    @Id
    @GeneratedValue
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
    @ManyToOne
    private Warehouse warehouse;


    @JoinColumn(name = "SUPPLIER",
            foreignKey = @ForeignKey(name = "FK_MAT_SPL"))
    @ManyToOne
    private Supplier supplier;
}
