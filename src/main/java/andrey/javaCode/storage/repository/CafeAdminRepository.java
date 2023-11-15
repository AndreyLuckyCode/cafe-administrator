package andrey.javaCode.storage.repository;

import andrey.javaCode.storage.entities.CafeAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeAdminRepository extends JpaRepository<CafeAdminEntity, Long> {
}
