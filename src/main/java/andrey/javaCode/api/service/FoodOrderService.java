package andrey.javaCode.api.service;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.FoodOrderDTO;
import andrey.javaCode.storage.entities.FoodOrderEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FoodOrderService {

    public FoodOrderDTO createFoodOrder(
            @PathVariable(name = "waiter_id") Long id,
            @ModelAttribute FoodOrderEntity order);

    public List<FoodOrderDTO> getFoodOrders(
            @PathVariable(name = "waiter_id") Long id);

    public FoodOrderDTO updateFoodOrder(
            @PathVariable(name = "food_order_id") Long id,
            @RequestParam(name = "food_order_bill", required = false) Integer foodOrderBill,
            @RequestParam(name = "tips_for_food", required = false) Integer tipsForFood);

    public AckDto deleteFoodOrder(
            @PathVariable(name = "food_order_id") Long id);
}
