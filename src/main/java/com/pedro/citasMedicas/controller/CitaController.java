package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.dto.CitaDTO;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;
    @GetMapping(value = "/ver")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<CitaDTO> obtenerCita(){
        return citaService.obtenerCita();
    }
    @PostMapping(value = "/crear")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CitaDTO insertarCita(@RequestBody Cita cita){
        return citaService.insertarCita(cita);
    }
    @GetMapping(value = "/ver/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CitaDTO verCita(@PathVariable Long id){
        return citaService.verCita(id);
    }
    @DeleteMapping(value="/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String CitaDTO(@PathVariable Long id){
        return citaService.eliminarCita(id);
    }
    @PutMapping(value = "/actualizar/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public CitaDTO actualizarCita(@PathVariable Long id, @RequestBody Cita cita){
        return citaService.actualizarCita(cita, id);
    }
}
