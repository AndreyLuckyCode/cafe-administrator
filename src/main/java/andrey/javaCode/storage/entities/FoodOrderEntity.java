package andrey.javaCode.storage.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "food_order")
public class FoodOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Builder.Default
    @JsonProperty("food_order_date")
    Instant foodOrderDate = Instant.now();

    @JsonProperty("tips_for_food")
    Integer tipsForFood;

    @JsonProperty("food_order_bill")
    Integer foodOrderBill;

    @OneToOne
    WaiterEntity waiter;
}
