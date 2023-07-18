package proces;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRICE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Price {

    @Id
    @GeneratedValue
    @Column(name = "PRICE_ID")
    @EqualsAndHashCode.Include
    private Long id;


    @PrimaryKeyJoinColumn(name = "PURCHASEPRICE")
    @OneToOne
    private PurchasePrice purchasePrice;


    @PrimaryKeyJoinColumn(name = "SALEPRICE")
    @OneToOne
    private SalePrice salePrice;
}
