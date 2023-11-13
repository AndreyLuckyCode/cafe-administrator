package andrey.javaCode.api.factory;

import andrey.javaCode.api.dto.FoodOrderDTO;
import andrey.javaCode.storage.entities.FoodOrderEntity;
import org.springframework.stereotype.Component;

@Component
public class FoodOrderDTOFactory {

    public FoodOrderDTO createFoodOrderDTO (FoodOrderEntity entity){

        return FoodOrderDTO.builder()
                .id(entity.getId())
                .foodOrderDate(entity.getFoodOrderDate())
                .tipsForFood(entity.getTipsForFood())
                .build();
    }
}
