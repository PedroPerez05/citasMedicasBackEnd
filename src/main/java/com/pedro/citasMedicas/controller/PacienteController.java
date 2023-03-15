package com.pedro.citasMedicas.controller;

import com.pedro.citasMedicas.dto.PacienteDTO;
import com.pedro.citasMedicas.model.Paciente;
import com.pedro.citasMedicas.model.Usuario;
import com.pedro.citasMedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/ver") //Get es el verbo, y lo que se pone dentro es el nombre de la url que tienes que poner para que se ejecute la funcion
    @Secured({"ROLE_USER", "ROLE_ADMIN"}) //Solo pueden acceder los roles de usuario y admin (solo tenemos esos)
    public List<PacienteDTO> obtenerPacientes(){
        List<PacienteDTO> pacientes=pacienteService.obtenerPacientes();
        System.out.println(pacientes);
        return pacientes;
    }
    @PostMapping(value = "/crear")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PacienteDTO insertarPaciente(@RequestBody Paciente paciente){
        return pacienteService.insertarPaciente(paciente);
    }
    @GetMapping(value = "/ver/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public PacienteDTO verPaciente(@PathVariable Long id){
        return pacienteService.verPaciente(id);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminarPaciente(@PathVariable Long id){
        return pacienteService.eliminarPaciente(id);
    }
    @PutMapping(value = "/actualizar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PacienteDTO modificarPaciente(@RequestBody Paciente paciente, @PathVariable Long id){
        return pacienteService.modificarPaciente(paciente, id);
    }
}
