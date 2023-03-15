package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.dto.DiagnosticoDTO;
import com.pedro.citasMedicas.model.Diagnostico;
import com.pedro.citasMedicas.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService diagnosticoService;
    @GetMapping(value = "/ver")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<DiagnosticoDTO> obtenerDiagnosticos(){
        return diagnosticoService.obtenerDiagnosticos();
    }
    @PostMapping(value = "/crear")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Diagnostico insertarDiagnostico(@RequestBody Diagnostico diagnostico){
        return diagnosticoService.insertarDiagnostico(diagnostico);
    }
    @GetMapping(value = "/ver/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public Diagnostico verDiagnostico(@PathVariable Long id){
        return diagnosticoService.verDiagnostico(id);
    }
    @DeleteMapping(value="/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminarDiagnostico(@PathVariable Long id){
        return diagnosticoService.eliminarDiagnostico(id);
    }
    @PutMapping(value = "/actualizar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public DiagnosticoDTO actualizarDiagnostico(@PathVariable Long id, @RequestBody Diagnostico diagnostico){
        return diagnosticoService.actualizarDiagnostico(diagnostico, id);
    }


}
