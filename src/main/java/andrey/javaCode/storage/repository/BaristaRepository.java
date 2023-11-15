package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.BaristaEntity;
import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaristaRepository extends JpaRepository<BaristaEntity, Long> {
    List<BaristaEntity> findAllByCafeAdminId(Long id);
}
