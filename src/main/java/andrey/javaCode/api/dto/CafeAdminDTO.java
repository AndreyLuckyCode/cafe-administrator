package andrey.javaCode.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CafeAdminDTO {

    @NonNull
    Long id;

    @NonNull
    String firstname;

    @NonNull
    String lastname;

    Integer salary;
}
