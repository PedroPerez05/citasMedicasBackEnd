package com.pedro.citasMedicas.service;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Medico;
import com.pedro.citasMedicas.repository.CitaRepository;
import com.pedro.citasMedicas.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired //Esta anotacion hace que spring boot te resuelva automaticamente el constructor de este atributo, tiene mas funcionalidades
    private MedicoRepository medicoRepository;
    @Autowired
    private CitaRepository citaRepository;
    public List<Medico> obtenerMedicos(){
        return medicoRepository.findAll();
    }
    public Medico verMedico(Long id){
        return medicoRepository.findById(id).orElse(null);
    }
    @Transactional
    public Medico insertarMedico(Medico medico){
        medicoRepository.save(medico);
        if(medico.getCitas()!=null) { //probando (no me inserta cuando creo un medico con cita, la cita en la tabla cita)
            for (Cita cita : medico.getCitas()) {
                //cita.setMedico(medico); Esto para que la cita se vincule al id, lo crea pero me salta una excepcion, puede ser por un bucle
                citaRepository.save(cita);
            }
        }else{
            System.out.println("Citas vacias");
        }
        return medico;
    }
    public String eliminarMedico(Long id){
       if(medicoRepository.existsById(id)) {
           medicoRepository.deleteById(id);
           return "Medico borrado";
       }else{
           return "El medico no existe";
       }
    }
    public Medico modificarMedico(Medico medico, Long id){
        Medico medicoTabla=medicoRepository.findById(id).orElse(null);
        medicoTabla.setPacientes(medico.getPacientes());
        medicoTabla.setNumColegiado(medico.getNumColegiado());
        medicoTabla.setClave(medico.getClave());
        medicoTabla.setNombre(medico.getNombre());
        medicoTabla.setApellidos(medico.getApellidos());
        medicoTabla.setUsuario(medico.getUsuario());
        medicoTabla.setCitas(medico.getCitas());
        medicoRepository.save(medicoTabla);
        return medicoTabla;
    }
}
