package andrey.javaCode.api.service.impl;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CafeAdminDTO;
import andrey.javaCode.api.factory.CafeAdminDTOFactory;
import andrey.javaCode.api.service.CafeAdminService;
import andrey.javaCode.storage.entities.CafeAdminEntity;
import andrey.javaCode.storage.repository.CafeAdminRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class CafeAdminServiceImpl implements CafeAdminService {


    CafeAdminRepository cafeAdminRepository;

    CafeAdminDTOFactory cafeAdminDTOFactory;

    @Override
    public CafeAdminDTO createAdmin(
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "salary") Integer salary) {

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
    public List<CafeAdminDTO> getAdmins() {
        return null;
    }

    @Override
    public CafeAdminDTO updateAdmin() {
        return null;
    }

    @Override
    public AckDto deleteAdmin() {
        return null;
    }
}
