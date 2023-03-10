package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    public List<Cita> obtenerCita(){
        return citaRepository.findAll();
    }
    public Cita insertarCita(Cita cita){
        return citaRepository.save(cita);
    }
    public String eliminarCita(Long id){
        if(citaRepository.existsById(id)){
            citaRepository.deleteById(id);
            return "Cita eliminada correctamente";
        }else{
            return "Cita no encontrado";
        }
    }
    public Cita actualizarCita(Cita cita, Long id){
        Cita citaTabla=citaRepository.findById(id).orElse(null);
        citaTabla.setMotivoCita(cita.getMotivoCita());
        citaTabla.setMedico(cita.getMedico());
        citaTabla.setPaciente(cita.getPaciente());
        citaTabla.setFechaHora(cita.getFechaHora());
        citaTabla.setDiagnostico(cita.getDiagnostico());
        return citaRepository.save(citaTabla);
    }
    public Cita verCita(Long id){
        return citaRepository.findById(id).orElse(null);
    }
}
