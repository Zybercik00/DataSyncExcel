package proces;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CURRENCY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Currency {

    @Id
    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    private Long id;

    @EqualsAndHashCode.Include
    @Column(name = "CURRENCY_NAME")
    private String currency;

    @OneToMany(mappedBy = "currency")
    private List<PurchasePrice> purchasePrices;
}
