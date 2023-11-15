package andrey.javaCode.api.factory;

import andrey.javaCode.api.dto.CoffeeOrderDTO;
import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import org.springframework.stereotype.Component;

@Component
public class CoffeeOrderDTOFactory {

    public CoffeeOrderDTO createCoffeeOrderDTO (CoffeeOrderEntity entity){

        return CoffeeOrderDTO.builder()
                .id(entity.getId())
                .coffeeOrderDate(entity.getCoffeeOrderDate())
                .tipsForCoffee(entity.getTipsForCoffee())
                .coffeeOrderBill(entity.getCoffeeOrderBill())
                .build();
    }
}
