package andrey.javaCode.storage.entities;

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
@Table(name = "cafe_admin")
public class CafeAdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String firstname;

    String lastname;

    Integer salary;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "cafe_admin_id", referencedColumnName = "id")
    List<WaiterEntity> waiterList = new ArrayList<>();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "cafe_admin_id", referencedColumnName = "id")
    List<BaristaEntity> baristaList = new ArrayList<>();
}
