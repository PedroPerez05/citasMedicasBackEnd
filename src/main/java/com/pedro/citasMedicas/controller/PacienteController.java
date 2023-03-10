package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.model.Paciente;
import com.pedro.citasMedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping(value = "/pacientes") //Get es el verbo, y lo que se pone dentro es el nombre de la url que tienes que poner para que se ejecute la funcion
    public List<Paciente> obtenerPacientes(){
        List<Paciente> pacientes=pacienteService.obtenerPacientes();
        //System.out.println(pacientes);
        return pacientes;
    }
    @PostMapping(value = "/pacientes")
    public Paciente insertarPaciente(@RequestBody Paciente paciente){
        return pacienteService.insertarPaciente(paciente);
    }
    @GetMapping(value = "/paciente/{id}")
    public Paciente verPaciente(@PathVariable Long id){
        return pacienteService.verPaciente(id);
    }

    @DeleteMapping(value = "/pacientes/{id}")
    public String eliminarPaciente(@PathVariable Long id){
        return pacienteService.eliminarPaciente(id);
    }
    @PutMapping(value = "/pacientes/{id}")
    public Paciente modificarPaciente(@RequestBody Paciente paciente, @PathVariable Long id){
        return pacienteService.modificarPaciente(paciente, id);
    }
}
