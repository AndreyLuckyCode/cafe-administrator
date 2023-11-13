package andrey.javaCode.api.factory;

import andrey.javaCode.api.dto.CafeAdminDTO;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import org.springframework.stereotype.Component;

@Component
public class CafeAdminDTOFactory {

public CafeAdminDTO createCafeAdminDTO(CafeAdminEntity entity){

return CafeAdminDTO.builder()
        .id(entity.getId())
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .salary(entity.getSalary())
        .build();
    }
}
