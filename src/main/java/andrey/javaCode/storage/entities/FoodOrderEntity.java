package andrey.javaCode.storage.entities;


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
    Instant foodOrderDate = Instant.now();

    Integer tipsForFood;

    @OneToOne
    WaiterEntity waiter;
}
