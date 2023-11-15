package andrey.javaCode.api.factory;

import andrey.javaCode.api.dto.WaiterDTO;
import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.stereotype.Component;

@Component
public class WaiterDTOFactory {

    public WaiterDTO createWaiterDTO(WaiterEntity entity){

        return WaiterDTO.builder()
                .id(entity.getId())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .salary(entity.getSalary())
                .workingDaysPerMonth(entity.getWorkingDaysPerMonth())
                .tablesServedPerMonth(entity.getTablesServedPerMonth())
                .waiterTips(entity.getWaiterTips())
                .build();
    }
}
