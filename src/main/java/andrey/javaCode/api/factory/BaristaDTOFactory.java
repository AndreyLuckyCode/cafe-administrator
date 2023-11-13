package andrey.javaCode.api.factory;

import andrey.javaCode.api.dto.BaristaDTO;
import andrey.javaCode.storage.entities.BaristaEntity;
import org.springframework.stereotype.Component;

@Component
public class BaristaDTOFactory {

    public BaristaDTO createBaristaDTO (BaristaEntity entity){

        return BaristaDTO.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .salary(entity.getSalary())
                .workingDaysPerMonth(entity.getWorkingDaysPerMonth())
                .clientsPerMonth(entity.getClientsPerMonth())
                .baristaTips(entity.getBaristaTips())
                .build();
    }
}
