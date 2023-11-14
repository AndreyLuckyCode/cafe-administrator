package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.WaiterDTO;
import andrey.javaCode.api.exceptions.BadRequestException;
import andrey.javaCode.api.exceptions.NotFoundException;
import andrey.javaCode.api.factory.WaiterDTOFactory;
import andrey.javaCode.api.service.WaiterService;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import andrey.javaCode.storage.entities.WaiterEntity;
import andrey.javaCode.storage.repository.CafeAdminRepository;
import andrey.javaCode.storage.repository.WaiterRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WaiterServiceImpl implements WaiterService {

    WaiterRepository waiterRepository;

    WaiterDTOFactory waiterDTOFactory;

    CafeAdminRepository cafeAdminRepository;


    @Override
    @Transactional
    public WaiterDTO createWaiter(
            @PathVariable(name = "admin_id") Long id,
            @ModelAttribute WaiterEntity waiter) {

        CafeAdminEntity admin = cafeAdminRepository.findById(id).orElseThrow(()
                -> new BadRequestException("Admin with this id doesn't exist"));

        waiter.setCafeAdmin(admin);

        if(waiter.getFirstname().trim().isEmpty()){
            throw new BadRequestException("Waiter firstname can't be empty");
        }

        if(waiter.getLastname().trim().isEmpty()){
            throw new BadRequestException("Waiter lastname can't be empty");
        }

        waiterRepository.saveAndFlush(waiter);

        return waiterDTOFactory.createWaiterDTO(waiter);
    }


    @Override
    @Transactional
    public List<WaiterDTO> getWaiters(
            @PathVariable(name = "admin_id") Long id) {


        Optional<CafeAdminEntity> admin = cafeAdminRepository.findById(id);

        if(admin.isEmpty()) {
            throw new NotFoundException("Admin with that id doesn't exist");
        }

        List<WaiterEntity> waiters = waiterRepository.findAllByCafeAdminId(id);

        if(waiters.isEmpty()){
            throw new NotFoundException("This admin doesn't have any waiters");
        }


        return waiters.stream()
                .map(waiterDTOFactory::createWaiterDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public WaiterDTO updateWaiter(
            @PathVariable(name = "waiter_id") Long id,
            @ModelAttribute WaiterEntity waiter) {

        WaiterEntity waiterEntity = waiterRepository.findById(id).orElseThrow(()
                -> new BadRequestException("Waiter with this id doesn't exist"));


        if(waiter.getFirstname() != null && !waiter.getFirstname().trim().isEmpty()){
            waiterEntity.setFirstname(waiter.getFirstname());
        }


        if(waiter.getLastname() != null && !waiter.getLastname().trim().isEmpty()){
            waiterEntity.setLastname(waiter.getLastname());
        }


        if(waiter.getSalary() != null){
            waiterEntity.setSalary(waiter.getSalary());
        }


        if(waiter.getWorkingDaysPerMonth() != null){
            waiterEntity.setWorkingDaysPerMonth(waiter.getWorkingDaysPerMonth());
        }


        if(waiter.getTablesServedPerMonth() != null){
            waiterEntity.setTablesServedPerMonth(waiter.getTablesServedPerMonth());
        }


        if(waiter.getWaiterTips() != null){
            waiterEntity.setWaiterTips(waiter.getWaiterTips());
        }


        waiterRepository.saveAndFlush(waiterEntity);

        return waiterDTOFactory.createWaiterDTO(waiterEntity);
    }


    @Override
    @Transactional
    public AckDto deleteWaiter(
            @PathVariable(name = "waiter_id") Long id) {


        if(waiterRepository.findById(id).isEmpty()){
            throw new BadRequestException("Waiter with this id doesn't exist.");
        }

        waiterRepository.deleteById(id);

        return AckDto.builder().answer(true).build();
    }
}
