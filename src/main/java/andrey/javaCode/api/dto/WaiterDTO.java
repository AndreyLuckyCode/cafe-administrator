package andrey.javaCode.api.dto;

import andrey.javaCode.storage.entities.FoodOrderEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WaiterDTO {

    @NonNull
    Long id;

    @NonNull
    String firstname;

    @NonNull
    String lastname;

    Integer salary;

    @JsonProperty("working_days_per_month")
    Integer workingDaysPerMonth;

    @JsonProperty("tables_served_per_month")
    Integer tablesServedPerMonth;

    @JsonProperty("waiter_tips")
    Integer waiterTips;

    @JsonProperty("food_orders")
    List<FoodOrderEntity> foodOrders;
}
