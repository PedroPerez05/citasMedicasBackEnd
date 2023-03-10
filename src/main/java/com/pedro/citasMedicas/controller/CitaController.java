package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Diagnostico;
import com.pedro.citasMedicas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CitaController {
    @Autowired
    private CitaService citaService;
    @GetMapping(value = "/citas")
    public List<Cita> obtenerCita(){
        return citaService.obtenerCita();
    }
    @PostMapping(value = "/citas")
    public Cita insertarCita(@RequestBody Cita cita){
        return citaService.insertarCita(cita);
    }
    @GetMapping(value = "/cita/{id}")
    public Cita verDiagnostico(@PathVariable Long id){
        return citaService.verCita(id);
    }
    @DeleteMapping(value="/citas/{id}")
    public String eliminarCita(@PathVariable Long id){
        return citaService.eliminarCita(id);
    }
    @PutMapping(value = "/citas/{id}")
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Cita cita){
        return citaService.actualizarCita(cita, id);
    }
}
