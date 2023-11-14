package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WaiterRepository extends JpaRepository<WaiterEntity, Long> {
    List<WaiterEntity> findAllByCafeAdminId(Long id);
}
