package andrey.javaCode.storage.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    Long id;

    String firstname;

    String lastname;

    Integer salary;

    @JsonProperty("working_days_per_month")
    Integer workingDaysPerMonth;

    @JsonProperty("tables_served_per_month")
    Integer tablesServedPerMonth;

    @JsonProperty("waiter_tips")
    Integer waiterTips;

    @OneToOne
    CafeAdminEntity cafeAdmin;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "waiter_id", referencedColumnName = "id")
    List<FoodOrderEntity> foodOrderList = new ArrayList<>();
}
