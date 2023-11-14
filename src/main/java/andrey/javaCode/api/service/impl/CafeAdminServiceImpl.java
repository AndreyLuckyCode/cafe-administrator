package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CafeAdminDTO;
import andrey.javaCode.api.exceptions.BadRequestException;
import andrey.javaCode.api.factory.CafeAdminDTOFactory;
import andrey.javaCode.api.service.CafeAdminService;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import andrey.javaCode.storage.repository.CafeAdminRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CafeAdminServiceImpl implements CafeAdminService{


    CafeAdminRepository cafeAdminRepository;

    CafeAdminDTOFactory cafeAdminDTOFactory;

    @Override
    @Transactional
    public CafeAdminDTO createAdmin(
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "salary") Integer salary) {


        if(firstname.trim().isEmpty()){
            throw new BadRequestException("Admin firstname can't be empty.");
        }

        if(lastname.trim().isEmpty()){
            throw new BadRequestException("Admin lastname can't be empty");
        }

        if(salary == null){
            throw new BadRequestException("Admin salary can't be empty");
        }


        CafeAdminEntity admin = cafeAdminRepository.saveAndFlush(
        CafeAdminEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .salary(salary)
                .build()
        );

        return cafeAdminDTOFactory.createCafeAdminDTO(admin);
    }


    @Override
    @Transactional
    public List<CafeAdminDTO> getAdmins() {

        List<CafeAdminEntity> admins = cafeAdminRepository.findAll();

        return admins.stream()
                .map(cafeAdminDTOFactory::createCafeAdminDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CafeAdminDTO updateAdmin(
            @PathVariable("admin_id") Long id,
            @RequestParam(name = "firstname", required = false) String firstname,
            @RequestParam(name = "lastname", required = false) String lastname,
            @RequestParam(name = "salary", required = false) Integer salary) {

        if(cafeAdminRepository.findById(id).isEmpty()){
            throw new BadRequestException("Admin with this id doesn't exist.");
        }


        CafeAdminEntity admin = cafeAdminRepository.getById(id);


        if(firstname != null){
            admin.setFirstname(firstname.trim());
        } else {
            throw new BadRequestException("Admin firstname can't be empty");
        }

        if(lastname != null) {
            admin.setLastname(lastname.trim());
        } else {
            throw new BadRequestException("Admin lastname can't be empty");
        }

        if(salary != null) {
            admin.setSalary(salary);
        } else {
            throw new BadRequestException("Admin salary can't be empty");
        }

        cafeAdminRepository.saveAndFlush(admin);


        return  cafeAdminDTOFactory.createCafeAdminDTO(admin);
    }


    @Override
    @Transactional
    public AckDto deleteAdmin(
            @PathVariable("admin_id") Long id) {

        if(cafeAdminRepository.findById(id).isEmpty()){
            throw new BadRequestException("Admin with this id doesn't exist.");
        }

        cafeAdminRepository.deleteById(id);

        return AckDto.builder().answer(true).build();
    }
}
