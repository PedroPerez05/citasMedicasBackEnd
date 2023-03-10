package com.pedro.citasMedicas.controller;


import com.pedro.citasMedicas.model.Medico;
import com.pedro.citasMedicas.repository.MedicoRepository;
//import com.pedro.citasMedicas.service.MedicoService;
import com.pedro.citasMedicas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    /*public MedicoController (MedicoService medicoService){
        this.medicoService=medicoService;
    }*/
    @GetMapping(value = "/medicos") //Get es el verbo, y lo que se pone dentro es el nombre de la url que tienes que poner para que se ejecute la funcion
    public List<Medico> devolverMedicos(){
        List<Medico> medicos=medicoService.obtenerMedicos();
        System.out.println(medicos);
        return medicos;
    }
    @PostMapping(value = "/medicos")
    public Medico insertarMedico(@RequestBody Medico medico){
        return medicoService.insertarMedico(medico);
    }
    @GetMapping(value = "/medico/{id}")
    public Medico verMedico(@PathVariable Long id){
        return medicoService.verMedico(id);
    }

    @DeleteMapping(value = "/medicos/{id}")
    public String eliminarMedico(@PathVariable Long id){
        return medicoService.eliminarMedico(id);
    }
    @PutMapping(value = "/medicos/{id}")
    public Medico modificarMedico(@RequestBody Medico medico, @PathVariable Long id){
        return medicoService.modificarMedico(medico, id);
    }
}
