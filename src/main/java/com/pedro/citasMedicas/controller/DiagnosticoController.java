package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.model.Diagnostico;
import com.pedro.citasMedicas.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService diagnosticoService;
    @GetMapping(value = "/diagnosticos")
    public List<Diagnostico> obtenerDiagnosticos(){
        return diagnosticoService.obtenerDiagnosticos();
    }
    @PostMapping(value = "/diagnosticos")
    public Diagnostico insertarDiagnostico(@RequestBody Diagnostico diagnostico){
        return diagnosticoService.insertarDiagnostico(diagnostico);
    }
    @GetMapping(value = "/diagnostico/{id}")
    public Diagnostico verDiagnostico(@PathVariable Long id){
        return diagnosticoService.verDiagnostico(id);
    }
    @DeleteMapping(value="/diagnosticos/{id}")
    public String eliminarDiagnostico(@PathVariable Long id){
        return diagnosticoService.eliminarDiagnostico(id);
    }
    @PutMapping(value = "/diagnosticos/{id}")
    public Diagnostico actualizarDiagnostico(@PathVariable Long id, @RequestBody Diagnostico diagnostico){
        return diagnosticoService.actualizarDiagnostico(diagnostico, id);
    }


}
