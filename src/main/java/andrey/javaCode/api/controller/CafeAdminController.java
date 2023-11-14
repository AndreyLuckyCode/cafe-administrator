package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.CafeAdminDTO;
import andrey.javaCode.api.service.CafeAdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class CafeAdminController {

    CafeAdminService cafeAdminService;

    private static final String  CREATE_CAFE_ADMIN= "api/admins";
    private static final String  GET_CAFE_ADMINS= "api/admins";
    private static final String  UPDATE_CAFE_ADMIN= "api/admins/{admin_id}";
    private static final String  DELETE_CAFE_ADMIN= "api/admins/{admin_id}";

    @PostMapping(CREATE_CAFE_ADMIN)
    public CafeAdminDTO createAdmin(
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "salary") Integer salary){

        return cafeAdminService.createAdmin(
                firstname,
                lastname,
                salary
        );
    }


    @GetMapping(GET_CAFE_ADMINS)
    public List<CafeAdminDTO> getAdmins(){

       return cafeAdminService.getAdmins();
    }


    @PatchMapping(UPDATE_CAFE_ADMIN)
    public CafeAdminDTO updateAdmin(
            @PathVariable("admin_id") Long id,
            @RequestParam(name = "firstname", required = false) String firstname,
            @RequestParam(name = "lastname", required = false) String lastname,
            @RequestParam(name = "salary", required = false) Integer salary){

        return cafeAdminService.updateAdmin(
                id,
                firstname,
                lastname,
                salary
        );
    }


    @DeleteMapping(DELETE_CAFE_ADMIN)
    public AckDto deleteAdmin(
            @PathVariable("admin_id") Long id){

        return cafeAdminService.deleteAdmin(id);
    }
}
