package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.CafeAdminDTO;
import andrey.javaCode.api.service.CafeAdminService;
import andrey.javaCode.api.service.impl.CafeAdminServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class CafeAdminController {

    CafeAdminService cafeAdminService;

    private static final String  CREATE_CAFE_ADMIN= "api/admins";
    private static final String  GET_CAFE_ADMINS= "api/admins";
    private static final String  UPDATE_CAFE_ADMIN= "api/admins/{admin_id}";
    private static final String  DELETE_CAFE_ADMIN= "api/admins/{admin_id}";

    @PostMapping(CREATE_CAFE_ADMIN)
    public CafeAdminDTO createAdmin(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam Integer salary){

        return cafeAdminService.createAdmin(
                firstname,
                lastname,
                salary
        );
    }
}
