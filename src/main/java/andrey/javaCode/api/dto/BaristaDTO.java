package andrey.javaCode.api.dto;

import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaristaDTO {

    @NonNull
    UUID id;

    @NonNull
    String firstname;

    @NonNull
    String lastname;

    Integer salary;

    @JsonProperty("working_days_per_month")
    Integer workingDaysPerMonth;

    @JsonProperty("clients_per_month")
    Integer clientsPerMonth;

    @JsonProperty("barista_tips")
    Integer baristaTips;

    @JsonProperty("coffee_orders")
    List<CoffeeOrderEntity> coffeeOrders;
}
