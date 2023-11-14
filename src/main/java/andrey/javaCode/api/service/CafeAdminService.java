package andrey.javaCode.api.service;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CafeAdminDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CafeAdminService {

    public CafeAdminDTO createAdmin(
            @RequestParam(name = "cafe_admin_firstname") String cafeAdminFirstName,
            @RequestParam(name = "cafe_admin_lastname") String cafeAdminLastname,
            @RequestParam(name = "cafe_admin_salary") Integer cafeAdminSalary);
    public List<CafeAdminDTO> getAdmins();
    public CafeAdminDTO updateAdmin();
    public AckDto deleteAdmin();
}
