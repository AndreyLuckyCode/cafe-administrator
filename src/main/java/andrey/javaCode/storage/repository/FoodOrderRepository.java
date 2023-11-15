package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import andrey.javaCode.storage.entities.FoodOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrderEntity, Long> {

    List<FoodOrderEntity> findAllByWaiterId(Long id);

}
