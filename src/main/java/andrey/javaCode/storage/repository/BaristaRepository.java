package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.BaristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaristaRepository extends JpaRepository<BaristaEntity, Long> {
}
