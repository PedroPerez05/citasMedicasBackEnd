package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.dto.DiagnosticoDTO;
import com.pedro.citasMedicas.dto.DiagnosticoDTOSimp;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Diagnostico;
import com.pedro.citasMedicas.repository.CitaRepository;
import com.pedro.citasMedicas.repository.DiagnosticoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private CitaRepository citaRepository;
    private ModelMapper mapper = new ModelMapper();
    public List<DiagnosticoDTO> obtenerDiagnosticos(){
        List<DiagnosticoDTO> diagnosticosDTO= new ArrayList<DiagnosticoDTO>();
        List<Diagnostico> diagnosticos=diagnosticoRepository.findAll();
        for (Diagnostico diagnostico: diagnosticos){
            diagnosticosDTO.add(mapper.map(diagnostico, DiagnosticoDTO.class)); //mapeamos para evitar el bucle
        }
        return diagnosticosDTO;
    }
    public Diagnostico insertarDiagnostico(Diagnostico diagnostico){ //No he conseguido crear un diagnostico una cita, estableciendo la relacion
        citaRepository.save(diagnostico.getCita());
        diagnosticoRepository.save(diagnostico);
        return diagnostico;
    }
    public String eliminarDiagnostico(Long id){
        if(diagnosticoRepository.existsById(id)){
            diagnosticoRepository.deleteById(id);
            return "Diagnostico eliminado correctamente";
        }else{
            return "Diagnostico no encontrado";
        }
    }
    public DiagnosticoDTO actualizarDiagnostico(Diagnostico diagnostico, Long id){ //Vamos a dejar que desde diagnostico solo pueda modificarse su cita
        Diagnostico diagnosticoTabla=diagnosticoRepository.findById(id).orElse(null);
        if(diagnostico.getEnfermedad()!=null) {
            diagnosticoTabla.setEnfermedad(diagnostico.getEnfermedad());
        }
        if(diagnostico.getValoracionEspecialista()!=null){
            diagnosticoTabla.setValoracionEspecialista(diagnostico.getValoracionEspecialista());
        }
        if(diagnostico.getCita()!=null){
            Cita cita=citaRepository.findById(diagnosticoTabla.getCita().getId()).orElse(null);
            cita.setMotivoCita(diagnostico.getCita().getMotivoCita());
            cita.setFechaHora(diagnostico.getCita().getFechaHora());
            citaRepository.save(cita);
        }
        diagnosticoRepository.save(diagnosticoTabla);
        return mapper.map(diagnosticoTabla, DiagnosticoDTO.class);
    }
    public Diagnostico verDiagnostico(Long id){
        return diagnosticoRepository.findById(id).orElse(null);
    }
}
