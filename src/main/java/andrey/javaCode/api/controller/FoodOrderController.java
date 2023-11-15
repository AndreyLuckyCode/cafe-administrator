package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.FoodOrderDTO;
import andrey.javaCode.api.service.FoodOrderService;
import andrey.javaCode.storage.entities.FoodOrderEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class FoodOrderController {

    FoodOrderService foodOrderService;

    private static final String CREATE_FOOD_ORDER  = "/api/waiter/{waiter_id}/food_order";
    private static final String GET_FOOD_ORDERS  = "/api/waiter/{waiter_id}/food_order";
    private static final String UPDATE_FOOD_ORDER  = "/api/food_order/{food_order_id}";
    private static final String DELETE_FOOD_ORDER  = "/api/food_order/{food_order_id}";


    @PostMapping(CREATE_FOOD_ORDER)
    public FoodOrderDTO createFoodOrder(
            @PathVariable(name = "waiter_id") Long id,
            @ModelAttribute FoodOrderEntity order){

        return foodOrderService.createFoodOrder(id, order);
    }


    @GetMapping(GET_FOOD_ORDERS)
    public List<FoodOrderDTO> getFoodOrders(
            @PathVariable(name = "waiter_id") Long id){

        return foodOrderService.getFoodOrders(id);
    }


    @PatchMapping(UPDATE_FOOD_ORDER)
    public FoodOrderDTO updateFoodOrder(
            @PathVariable(name = "food_order_id") Long id,
            @RequestParam(name = "food_order_bill", required = false) Integer foodOrderBill,
            @RequestParam(name = "tips_for_food", required = false) Integer tipsForFood){

        return foodOrderService.updateFoodOrder(id, foodOrderBill, tipsForFood);
    }


    @DeleteMapping(DELETE_FOOD_ORDER)
    public AckDto deleteFoodOrder(
            @PathVariable(name = "food_order_id") Long id){

        return foodOrderService.deleteFoodOrder(id);
    }
}
