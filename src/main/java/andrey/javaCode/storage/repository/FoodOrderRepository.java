package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.FoodOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodOrderRepository extends JpaRepository<FoodOrderEntity, UUID> {
}
