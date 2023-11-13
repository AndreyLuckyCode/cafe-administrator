package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.BaristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaristaRepository extends JpaRepository<BaristaEntity, UUID> {
}
