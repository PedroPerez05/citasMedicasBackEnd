package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.model.Diagnostico;
import com.pedro.citasMedicas.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public List<Diagnostico> obtenerDiagnosticos(){
        return diagnosticoRepository.findAll();
    }
    public Diagnostico insertarDiagnostico(Diagnostico diagnostico){
        return diagnosticoRepository.save(diagnostico);
    }
    public String eliminarDiagnostico(Long id){
        if(diagnosticoRepository.existsById(id)){
            diagnosticoRepository.deleteById(id);
            return "Diagnostico eliminado correctamente";
        }else{
            return "Diagnostico no encontrado";
        }
    }
    public Diagnostico actualizarDiagnostico(Diagnostico diagnostico, Long id){
        Diagnostico diagnosticoTabla=diagnosticoRepository.findById(id).orElse(null);
        diagnosticoTabla.setEnfermedad(diagnostico.getEnfermedad());
        diagnosticoTabla.setValoracionEspecialista(diagnostico.getValoracionEspecialista());
        return diagnosticoRepository.save(diagnosticoTabla);
    }
    public Diagnostico verDiagnostico(Long id){
        return diagnosticoRepository.findById(id).orElse(null);
    }
}
