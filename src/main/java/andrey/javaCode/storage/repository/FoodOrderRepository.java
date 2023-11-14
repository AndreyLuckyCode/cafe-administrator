package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.FoodOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrderEntity, Long> {
}
