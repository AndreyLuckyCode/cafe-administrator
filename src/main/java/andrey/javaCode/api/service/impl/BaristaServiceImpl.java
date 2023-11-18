package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.BaristaDTO;
import andrey.javaCode.api.exceptions.BadRequestException;
import andrey.javaCode.api.exceptions.NotFoundException;
import andrey.javaCode.api.factory.BaristaDTOFactory;
import andrey.javaCode.api.service.BaristaService;
import andrey.javaCode.storage.entities.BaristaEntity;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import andrey.javaCode.storage.repository.BaristaRepository;
import andrey.javaCode.storage.repository.CafeAdminRepository;
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
public class BaristaServiceImpl implements BaristaService {


    BaristaRepository baristaRepository;

    BaristaDTOFactory baristaDTOFactory;

    CafeAdminRepository cafeAdminRepository;


    @Override
    @Transactional
    public BaristaDTO createBarista(
            @PathVariable(name = "admin_id") Long id,
            @ModelAttribute BaristaEntity barista) {


        CafeAdminEntity admin = cafeAdminRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Admin with this id doesn't exist"));

        barista.setCafeAdmin(admin);

        if(barista.getFirstname().trim().isEmpty()){
            throw new BadRequestException("Waiter firstname can't be empty");
        }

        if(barista.getLastname().trim().isEmpty()){
            throw new BadRequestException("Waiter lastname can't be empty");
        }

        baristaRepository.saveAndFlush(barista);

        return baristaDTOFactory.createBaristaDTO(barista);
    }


    @Transactional
    public List<BaristaDTO> getBaristasByAdminId(
            @PathVariable(name = "admin_id") Long id) {


        Optional<CafeAdminEntity> admin = cafeAdminRepository.findById(id);

        if(admin.isEmpty()) {
            throw new NotFoundException("Admin with that id doesn't exist");
        }

        List<BaristaEntity> baristas = baristaRepository.findAllByCafeAdminId(id);

        if(baristas.isEmpty()){
            throw new NotFoundException("This admin doesn't have any baristas");
        }


        return baristas.stream()
                .map(baristaDTOFactory::createBaristaDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public BaristaDTO updateBarista(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute BaristaEntity barista) {


        BaristaEntity baristaEntity = baristaRepository.findById(id).orElseThrow(()
                -> new NotFoundException("Barista with this id doesn't exist"));


        if(barista.getFirstname() != null && !barista.getFirstname().trim().isEmpty()){
            baristaEntity.setFirstname(barista.getFirstname());
        }


        if(barista.getLastname() != null && !barista.getLastname().trim().isEmpty()){
            baristaEntity.setLastname(barista.getLastname());
        }


        if(barista.getSalary() != null){
            baristaEntity.setSalary(barista.getSalary());
        }


        if(barista.getWorkingDaysPerMonth() != null){
            baristaEntity.setWorkingDaysPerMonth(barista.getWorkingDaysPerMonth());
        }


        if(barista.getClientsPerMonth() != null){
            baristaEntity.setClientsPerMonth(barista.getClientsPerMonth());
        }


        if(barista.getBaristaTips() != null){
            baristaEntity.setBaristaTips(barista.getBaristaTips());
        }


        baristaRepository.saveAndFlush(baristaEntity);

        return baristaDTOFactory.createBaristaDTO(baristaEntity);
    }


    @Override
    @Transactional
    public AckDto deleteBarista(
            @PathVariable(name = "barista_id") Long id) {


        if(baristaRepository.findById(id).isEmpty()){
            throw new NotFoundException("Barista with this id doesn't exist.");
        }

        baristaRepository.deleteById(id);

        return AckDto.builder().answer(true).build();
    }
}
