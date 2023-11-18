package andrey.javaCode.api.service;

import andrey.javaCode.api.dto.AckDto;
import andrey.javaCode.api.dto.BaristaDTO;
import andrey.javaCode.api.dto.WaiterDTO;
import andrey.javaCode.storage.entities.BaristaEntity;
import andrey.javaCode.storage.entities.WaiterEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BaristaService {

    public BaristaDTO createBarista(
            @RequestParam(name = "admin_id") Long id,
            @ModelAttribute BaristaEntity baristaEntity);


    public List<BaristaDTO> getBaristasByAdminId(
            @PathVariable(name = "admin_id") Long id);


    public BaristaDTO updateBarista(
            @PathVariable(name = "barista_id") Long id,
            @ModelAttribute BaristaEntity barista);


    public AckDto deleteBarista(
            @PathVariable(name = "barista_id") Long id);
}
