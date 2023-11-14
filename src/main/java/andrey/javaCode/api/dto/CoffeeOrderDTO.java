package andrey.javaCode.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoffeeOrderDTO {

    @NonNull
    Long id;

    @NonNull
    @JsonProperty("coffee_order_date")
    Instant coffeeOrderDate = Instant.now();

    @NonNull
    @JsonProperty("tips_for_coffee")
    Integer tipsForCoffee;
}
