package andrey.javaCode.storage.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "barista")
public class BaristaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    UUID id;

    String firstname;

    String lastname;

    Integer salary;

    Integer workingDaysPerMonth;

    Integer clientsPerMonth;

    Integer baristaTips;

    @OneToOne
    CafeAdminEntity cafeAdmin;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "barista_id", referencedColumnName = "id")
    List<CoffeeOrderEntity> coffeeOrderList = new ArrayList<>();
}
