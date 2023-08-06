package com.github.zybercik00.entity.process;

import jakarta.persistence.*;
import lombok.*;
import com.github.zybercik00.entity.process.stock.Supplier;
import com.github.zybercik00.entity.process.stock.Warehouse;

@Entity
@Table(name = "MATERIAL")
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


    @JoinColumn(name = "WAREHOUSE")
    @ManyToOne
    private Warehouse warehouse;


    @PrimaryKeyJoinColumn(name = "PRICE")
    @OneToOne
    private Price price;


    @JoinColumn(name = "SUPPILER")
    @ManyToOne
    private Supplier supplier;
}
