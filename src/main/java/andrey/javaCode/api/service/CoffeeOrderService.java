package andrey.javaCode.api.service;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CoffeeOrderDTO;
import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CoffeeOrderService {

    public CoffeeOrderDTO createCoffeeOrder(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute CoffeeOrderEntity order);


    public List<CoffeeOrderDTO> getCoffeeOrders(
            @PathVariable(name = "barista_id") Long id);


    public CoffeeOrderDTO updateCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id,
            @RequestParam(name = "coffee_order_bill", required = false) Integer coffeeOrderBill,
            @RequestParam(name = "tips_for_coffee", required = false) Integer tipsForCoffee);


    public AckDto deleteCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id);


    public String getBaristaTips(
            @PathVariable(name = "barista_id") Long id);
}
