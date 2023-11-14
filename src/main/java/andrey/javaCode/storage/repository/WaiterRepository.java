package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WaiterRepository extends JpaRepository<WaiterEntity, Long> {
}
