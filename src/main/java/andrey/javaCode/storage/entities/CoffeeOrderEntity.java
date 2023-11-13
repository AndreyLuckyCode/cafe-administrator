package andrey.javaCode.storage.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "coffee_order")
public class CoffeeOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    UUID id;

    @Builder.Default
    Instant coffeeOrderDate = Instant.now();

    Integer tipsForCoffee;

    @OneToOne
    BaristaEntity barista;
}
