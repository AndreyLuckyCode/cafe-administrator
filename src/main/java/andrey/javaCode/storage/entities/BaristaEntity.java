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
@Table(name = "barista")
public class BaristaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String firstname;

    String lastname;

    Integer salary;

    @JsonProperty("working_days_per_month")
    Integer workingDaysPerMonth;

    @JsonProperty("clients_per_month")
    Integer clientsPerMonth;

    @JsonProperty("barista_tips")
    Integer baristaTips;

    @OneToOne
    CafeAdminEntity cafeAdmin;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "barista_id", referencedColumnName = "id")
    List<CoffeeOrderEntity> coffeeOrderList = new ArrayList<>();
}
