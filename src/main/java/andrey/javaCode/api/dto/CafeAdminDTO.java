package andrey.javaCode.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CafeAdminDTO {

    @NonNull
    UUID id;

    @NonNull
    String firstname;

    @NonNull
    String lastname;

    Integer salary;
}
