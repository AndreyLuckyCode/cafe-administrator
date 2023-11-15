package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CoffeeOrderDTO;
import andrey.javaCode.api.service.CoffeeOrderService;
import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class CoffeeOrderController {

    CoffeeOrderService coffeeOrderService;

    private static final String CREATE_COFFEE_ORDER  = "/api/barista/{barista_id}/coffee_order";
    private static final String GET_COFFEE_ORDERS  = "/api/barista/{barista_id}/coffee_order";
    private static final String UPDATE_COFFEE_ORDER  = "/api/coffee_order/{coffee_order_id}";
    private static final String DELETE_COFFEE_ORDER  = "/api/coffee_order/{coffee_order_id}";


    @PostMapping(CREATE_COFFEE_ORDER)
    public CoffeeOrderDTO createCoffeeOrder(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute CoffeeOrderEntity order){

        return coffeeOrderService.createCoffeeOrder(id, order);
    }


    @GetMapping(GET_COFFEE_ORDERS)
    public List< CoffeeOrderDTO> getCoffeeOrders(
            @PathVariable(name = "barista_id") Long id){

        return coffeeOrderService.getCoffeeOrders(id);
    }


    @PatchMapping(UPDATE_COFFEE_ORDER)
    public CoffeeOrderDTO updateCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id,
            @RequestParam(name = "coffee_order_bill", required = false) Integer coffeeOrderBill,
            @RequestParam(name = "tips_for_coffee", required = false) Integer tipsForCoffee){

        return coffeeOrderService.updateCoffeeOrder(id, coffeeOrderBill, tipsForCoffee);
    }


    @DeleteMapping(DELETE_COFFEE_ORDER)
    public AckDto deleteCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id){

        return coffeeOrderService.deleteCoffeeOrder(id);
    }
}
