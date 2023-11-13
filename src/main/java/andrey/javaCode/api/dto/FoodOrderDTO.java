package andrey.javaCode.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodOrderDTO {

    @NonNull
    UUID id;

    @NonNull
    @JsonProperty("food_order_date")
    Instant foodOrderDate = Instant.now();

    @NonNull
    @JsonProperty("tips_for_food")
    Integer tipsForFood;
}
