package stock;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import proces.Material;

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
    @GeneratedValue
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
