package andrey.javaCode.api.service;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.WaiterDTO;
import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WaiterService {

    public WaiterDTO createWaiter(
            @RequestParam(name = "admin_id") Long id,
            @ModelAttribute WaiterEntity waiterEntity);


    public List<WaiterDTO> getWaitersByAdminId(
            @PathVariable(name = "admin_id") Long id);


    public WaiterDTO updateWaiter(
            @PathVariable(name = "waiter_id") Long id,
            @ModelAttribute WaiterEntity waiter);


    public AckDto deleteWaiter(
            @PathVariable(name = "waiter_id") Long id);
}
