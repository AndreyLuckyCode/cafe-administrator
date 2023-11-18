package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CoffeeOrderDTO;
import andrey.javaCode.api.exceptions.BadRequestException;
import andrey.javaCode.api.exceptions.NotFoundException;
import andrey.javaCode.api.factory.CoffeeOrderDTOFactory;
import andrey.javaCode.api.service.CoffeeOrderService;
import andrey.javaCode.storage.entities.BaristaEntity;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import andrey.javaCode.storage.entities.CoffeeOrderEntity;
import andrey.javaCode.storage.entities.WaiterEntity;
import andrey.javaCode.storage.repository.BaristaRepository;
import andrey.javaCode.storage.repository.CoffeeOrderRepository;
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
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

    CoffeeOrderRepository coffeeOrderRepository;

    CoffeeOrderDTOFactory coffeeOrderDTOFactory;

    BaristaRepository baristaRepository;


    @Override
    @Transactional
    public CoffeeOrderDTO createCoffeeOrder(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute CoffeeOrderEntity order) {

        BaristaEntity barista = baristaRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Barista with this id doesn't exist"));

        order.setBarista(barista);

        if(order.getCoffeeOrderBill() == null){
            throw new BadRequestException("Bill can't be null");
        }

        coffeeOrderRepository.saveAndFlush(order);

        return coffeeOrderDTOFactory.createCoffeeOrderDTO(order);
    }


    @Override
    @Transactional
    public List<CoffeeOrderDTO> getCoffeeOrders(
            @PathVariable(name = "barista_id") Long id) {


        Optional<BaristaEntity> barista = baristaRepository.findById(id);

        if (barista.isEmpty()) {
            throw new NotFoundException("Barista with that id doesn't exist");
        }

        List<CoffeeOrderEntity> orders = coffeeOrderRepository.findAllByBaristaId(id);

        if (orders.isEmpty()) {
            throw new NotFoundException("This barista doesn't have any orders");
        }


        return orders.stream()
                .map(coffeeOrderDTOFactory::createCoffeeOrderDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CoffeeOrderDTO updateCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id,
            @RequestParam(name = "coffee_order_bill", required = false) Integer coffeeOrderBill,
            @RequestParam(name = "tips_for_coffee", required = false) Integer tipsForCoffee) {


        CoffeeOrderEntity order = coffeeOrderRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Order with this id doesn't exist"));

        if(coffeeOrderBill != null){
            order.setCoffeeOrderBill(coffeeOrderBill);
        }

        if(tipsForCoffee != null){
            order.setTipsForCoffee(tipsForCoffee);
        }

        coffeeOrderRepository.saveAndFlush(order);

        return coffeeOrderDTOFactory.createCoffeeOrderDTO(order);
    }


    @Override
    @Transactional
    public AckDto deleteCoffeeOrder(
            @PathVariable(name = "coffee_order_id") Long id) {

        if(coffeeOrderRepository.findById(id).isEmpty()){
            throw new NotFoundException("Order with this id doesn't exist.");
        }

        coffeeOrderRepository.deleteById(id);

        return AckDto.builder().answer(true).build();
    }


    @Override
    @Transactional
    public String getBaristaTips(
            @PathVariable(name = "barista_id") Long id) {


        Optional<BaristaEntity> barista = baristaRepository.findById(id);

        if (barista.isEmpty()) {
            throw new NotFoundException("Barista with that id doesn't exist");
        }


        List<CoffeeOrderEntity> orders = coffeeOrderRepository.findAllByBaristaId(id);

        if (orders.isEmpty()) {
            throw new NotFoundException("This barista doesn't have any orders");
        }


        int totalTips = 0;
        for (CoffeeOrderEntity order : orders) {
            totalTips += order.getTipsForCoffee();
        }


        if(totalTips == 0){
            throw new NotFoundException("This barista doesn't have " +
                    "any tips for coffee orders");
        }

        return "Total tips of barista with this id: " + totalTips;
    }
}
