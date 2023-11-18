package andrey.javaCode.api.controller;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.BaristaDTO;
import andrey.javaCode.api.service.BaristaService;
import andrey.javaCode.api.service.WaiterService;
import andrey.javaCode.storage.entities.BaristaEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class BaristaController {

    BaristaService baristaService;

    private static final String CREATE_BARISTA = "/api/admins/{admin_id}/barista";
    private static final String GET_BARISTAS = "/api/admins/{admin_id}/barista";
    private static final String UPDATE_BARISTA = "/api/barista/{barista_id}";
    private static final String DELETE_BARISTA = "/api/barista/{barista_id}";


    @PostMapping(CREATE_BARISTA)
    public BaristaDTO createBarista(
            @PathVariable(name = "admin_id") Long id,
            @ModelAttribute BaristaEntity barista){

        return baristaService.createBarista(id, barista);
    }


    @GetMapping(GET_BARISTAS)
    public List<BaristaDTO> getBaristasByAdminId(
            @PathVariable(name = "admin_id") Long id){
        return baristaService.getBaristasByAdminId(id);
    }


    @PatchMapping(UPDATE_BARISTA)
    public BaristaDTO updateBarista(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute BaristaEntity barista){

        return baristaService.updateBarista(id,barista);
    }


    @DeleteMapping(DELETE_BARISTA)
    public AckDto deleteBarista(
            @PathVariable(name = "barista_id") Long id){

        return baristaService.deleteBarista(id);
    }
}
