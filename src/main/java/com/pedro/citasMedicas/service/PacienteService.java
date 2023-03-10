package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.model.Paciente;
import com.pedro.citasMedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;
    public List<Paciente> obtenerPacientes(){
        return pacienteRepository.findAll();
    }
    public Paciente verPaciente(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }
    public Paciente insertarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public String eliminarPaciente(Long id){
        if(pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return "paciente borrado";
        }else{
            return "El paciente no existe";
        }
    }
    public Paciente modificarPaciente(Paciente paciente, Long id){
        Paciente pacienteTabla=pacienteRepository.findById(id).orElse(null);
            pacienteTabla.setNombre(paciente.getNombre());
            pacienteTabla.setCitas(paciente.getCitas());
            pacienteTabla.setDireccion(paciente.getDireccion());
            pacienteTabla.setMedicos(paciente.getMedicos());
            pacienteTabla.setClave(paciente.getClave());
            pacienteTabla.setNumTarjeta(paciente.getNumTarjeta());
            pacienteTabla.setNSS(paciente.getNSS());
            pacienteTabla.setTelefono(paciente.getTelefono());
            pacienteRepository.save(pacienteTabla);
        return pacienteTabla;
    }
}
