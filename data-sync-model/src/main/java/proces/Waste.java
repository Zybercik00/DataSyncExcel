package proces;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WASTE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Waste {

    @Id
    @GeneratedValue
    @Column(name = "WASTE_ID")
    @EqualsAndHashCode.Include
    private Long id;


    @Column(name = "PACKED_KG")
    private double packedKg;

    @Column(name = "LOSS_AFTER_WASH_KG")
    private double lossAfterWashKg;

    @Column(name = "LOSS_AFTER_WASH_PERCENT")
    private double lossAfterWashPercent;

    @Column(name = "LOSS_TRIM")
    private double lossTrim;


    @Column(name = "LOSS_TOTAL_KG")
    private double lossTotalKg;


    @Column(name = "LOSS_TOTAL_PERCENTS")
    private double lossTotalPercents;
}
