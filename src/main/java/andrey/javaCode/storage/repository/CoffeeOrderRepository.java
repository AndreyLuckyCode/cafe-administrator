package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrderEntity, Long> {
}
