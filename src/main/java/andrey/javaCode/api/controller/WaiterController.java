package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.WaiterDTO;
import andrey.javaCode.api.service.WaiterService;
import andrey.javaCode.storage.entities.WaiterEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class WaiterController {

    WaiterService waiterService;

    private static final String CREATE_WAITER = "/api/admins/{admin_id}/waiter";
    private static final String GET_WAITERS = "/api/admins/{admin_id}/waiter";
    private static final String UPDATE_WAITER = "/api/waiter/{waiter_id}";
    private static final String DELETE_WAITER = "/api/waiter/{waiter_id}";


    @PostMapping(CREATE_WAITER)
    public WaiterDTO createWaiter(
            @PathVariable(name = "admin_id") Long id,
            @ModelAttribute WaiterEntity waiter){
        return waiterService.createWaiter(id, waiter);
    }


    @GetMapping(GET_WAITERS)
    public List<WaiterDTO> getWaiters (
            @PathVariable(name = "admin_id") Long id){

        return waiterService.getWaiters(id);
    }
}
