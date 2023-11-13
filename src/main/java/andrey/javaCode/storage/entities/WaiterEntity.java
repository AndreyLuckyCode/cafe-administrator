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
@Table(name = "waiter")
public class WaiterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    UUID id;

    String firstname;

    String lastname;

    Integer salary;

    Integer workingDaysPerMonth;

    Integer tablesServedPerMonth;

    Integer waiterTips;

    @OneToOne
    CafeAdminEntity cafeAdmin;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "waiter_id", referencedColumnName = "id")
    List<FoodOrderEntity> foodOrderList = new ArrayList<>();
}
