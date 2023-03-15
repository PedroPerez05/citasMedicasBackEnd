package com.pedro.citasMedicas.controller;


import com.pedro.citasMedicas.dto.MedicoDTO;
import com.pedro.citasMedicas.model.Medico;
import com.pedro.citasMedicas.repository.MedicoRepository;
//import com.pedro.citasMedicas.service.MedicoService;
import com.pedro.citasMedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    /*public MedicoController (MedicoService medicoService){
        this.medicoService=medicoService;
    }*/
    @GetMapping(value = "/ver") //Get es el verbo, y lo que se pone dentro es el nombre de la url que tienes que poner para que se ejecute la funcion
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<MedicoDTO> devolverMedicos(){
        List<MedicoDTO> medicos=medicoService.obtenerMedicos();
        System.out.println(medicos);
        return medicos;
    }
    @PostMapping(value = "/crear")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MedicoDTO insertarMedico(@RequestBody Medico medico){
        return medicoService.insertarMedico(medico);
    }
    @GetMapping(value = "/ver/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public MedicoDTO verMedico(@PathVariable Long id){
        return medicoService.verMedico(id);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String eliminarMedico(@PathVariable Long id){
        return medicoService.eliminarMedico(id);
    }
    @PutMapping(value = "/actualizar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MedicoDTO modificarMedico(@RequestBody Medico medico, @PathVariable Long id){
        return medicoService.modificarMedico(medico, id);
    }
}
