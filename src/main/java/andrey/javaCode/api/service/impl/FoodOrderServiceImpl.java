package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.FoodOrderDTO;
import andrey.javaCode.api.exceptions.BadRequestException;
import andrey.javaCode.api.exceptions.NotFoundException;
import andrey.javaCode.api.factory.FoodOrderDTOFactory;
import andrey.javaCode.api.service.FoodOrderService;
import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import andrey.javaCode.storage.entities.FoodOrderEntity;
import andrey.javaCode.storage.entities.WaiterEntity;
import andrey.javaCode.storage.repository.FoodOrderRepository;
import andrey.javaCode.storage.repository.WaiterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    FoodOrderRepository foodOrderRepository;

    FoodOrderDTOFactory foodOrderDTOFactory;

    WaiterRepository waiterRepository;


    @Override
    @Transactional
    public FoodOrderDTO createFoodOrder(
            @PathVariable(name = "waiter_id") Long id,
            @ModelAttribute FoodOrderEntity order) {

        WaiterEntity waiter = waiterRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Waiter with this id doesn't exist"));

        order.setWaiter(waiter);

        if(order.getFoodOrderBill() == null){
            throw new BadRequestException("Bill can't be null");
        }

        foodOrderRepository.saveAndFlush(order);

        return foodOrderDTOFactory.createFoodOrderDTO(order);
    }


    @Override
    @Transactional
    public List<FoodOrderDTO> getFoodOrders(
            @PathVariable(name = "waiter_id") Long id) {

        Optional<WaiterEntity> waiter = waiterRepository.findById(id);

        if (waiter.isEmpty()) {
            throw new NotFoundException("Waiter with that id doesn't exist");
        }


        List<FoodOrderEntity> orders = foodOrderRepository.findAllByWaiterId(id);

        if (orders.isEmpty()) {
            throw new NotFoundException("This waiter doesn't have any orders");
        }


        return orders.stream()
                .map(foodOrderDTOFactory::createFoodOrderDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public FoodOrderDTO updateFoodOrder(
            @PathVariable(name = "food_order_id") Long id,
            @RequestParam(name = "food_order_bill", required = false) Integer foodOrderBill,
            @RequestParam(name = "tips_for_food", required = false) Integer tipsForFood) {

        FoodOrderEntity order = foodOrderRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Order with this id doesn't exist"));


        if(foodOrderBill != null){
            order.setFoodOrderBill(foodOrderBill);
        }

        if(tipsForFood != null){
            order.setTipsForFood(tipsForFood);
        }

        foodOrderRepository.saveAndFlush(order);

        return foodOrderDTOFactory.createFoodOrderDTO(order);
    }


    @Override
    @Transactional
    public AckDto deleteFoodOrder(
            @PathVariable(name = "food_order_id") Long id) {

        if(foodOrderRepository.findById(id).isEmpty()){
            throw new NotFoundException("Order with this id doesn't exist.");
        }

        foodOrderRepository.deleteById(id);


        return AckDto.builder().answer(true).build();
    }


    @Override
    @Transactional
    public String getWaiterTips(Long id) {


        Optional<WaiterEntity> waiter = waiterRepository.findById(id);

        if (waiter.isEmpty()) {
            throw new NotFoundException("Waiter with that id doesn't exist");
        }


        List<FoodOrderEntity> orders = foodOrderRepository.findAllByWaiterId(id);

        if (orders.isEmpty()) {
            throw new NotFoundException("This waiter doesn't have any orders");
        }



        int totalTips = 0;
        for (FoodOrderEntity order : orders) {
            totalTips += order.getTipsForFood();
        }


        if(totalTips == 0){
            throw new NotFoundException("This waiter doesn't have " +
                    "any tips for coffee orders");
        }

        return "Total tips of waiter with this id: " + totalTips;
    }
}
