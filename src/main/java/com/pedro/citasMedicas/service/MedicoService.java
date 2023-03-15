package com.pedro.citasMedicas.service;
import com.pedro.citasMedicas.dto.MedicoDTO;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Medico;
import com.pedro.citasMedicas.model.Paciente;
import com.pedro.citasMedicas.repository.CitaRepository;
import com.pedro.citasMedicas.repository.DiagnosticoRepository;
import com.pedro.citasMedicas.repository.MedicoRepository;
import com.pedro.citasMedicas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired //Esta anotacion hace que spring boot te resuelva automaticamente el constructor de este atributo, tiene mas funcionalidades
    private MedicoRepository medicoRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper = new ModelMapper();
    public List<MedicoDTO> obtenerMedicos(){
        List<MedicoDTO> medicosDTO= new ArrayList<MedicoDTO>();
        List<Medico> medicos=medicoRepository.findAll();
        for (Medico medico: medicos){
            medicosDTO.add(mapper.map(medico, MedicoDTO.class)); //mapeamos para evitar el bucle
        }
        return medicosDTO;
    }
    public MedicoDTO verMedico(Long id){
        return mapper.map(medicoRepository.findById(id).orElse(null), MedicoDTO.class);
    }
    @Transactional
    public MedicoDTO insertarMedico(Medico medico){ //no es el mismo metodo que el insertarPaciente, ya que el mappedby lo tenemos en Medico
         //guardamos el medico en la base de datos
        medico.setClave(passwordEncoder.encode(medico.getClave()));
        medicoRepository.save(medico);
        if(medico.getPacientes()!=null) {

            for (Paciente paciente : medico.getPacientes()) { //A cada paciente de este medico le asignamos el medico para la tabla paciente_medico
                    List<Medico> medicos = paciente.getMedicos();
                    medicos.add(medico);
                    paciente.setMedicos(medicos);
                    if(paciente.getClave()!=null) { //no debe ser null, pero es para que no me salte error
                        paciente.setClave(passwordEncoder.encode(paciente.getClave()));
                    }
                    pacienteRepository.save(paciente);
            }
        }
        if(medico.getCitas()!=null) {
            MedicoDTO medicoDTO=mapper.map(medico, MedicoDTO.class);
            Medico m1=mapper.map(medicoDTO, Medico.class); //convertimos el medico a dto y luego a medico de nuevo, para que no se cree un bucle infinito de citas y medicos
            for (Cita cita : medico.getCitas()) { //debemos insertar manualmente la cita y el diagnostico
                cita.setMedico(m1);
                citaRepository.save(cita);
                if(cita.getDiagnostico()!=null) {//esto no haria falta porque la cita debe tener si o si un diagnostico
                    diagnosticoRepository.save(cita.getDiagnostico());
                }
            }
        }

        return mapper.map(medico, MedicoDTO.class); //devolvemos en dto para que no se pete por el bucle
    }
    public String eliminarMedico(Long id){
       if(medicoRepository.existsById(id)) {
           medicoRepository.deleteById(id);
           return "Medico borrado";
       }else{
           return "El medico no existe";
       }
    }
    public MedicoDTO modificarMedico(Medico medico, Long id){
        Medico medicoTabla=medicoRepository.findById(id).orElse(null);
        if(medico.getPacientes()!=null) {
            medicoTabla.setPacientes(medico.getPacientes());
        }
        if(medico.getNumColegiado()!=null) {
            medicoTabla.setNumColegiado(medico.getNumColegiado());
        }
        if(medico.getClave()!=null) {
            medicoTabla.setClave(medico.getClave());
        }
        if(medico.getNombre()!=null) {
            medicoTabla.setNombre(medico.getNombre());
        }
        if(medico.getApellidos()!=null) {
            medicoTabla.setApellidos(medico.getApellidos());
        }
        if(medico.getUsuario()!=null) {
            medicoTabla.setUsuario(medico.getUsuario());
        }
        if(medico.getCitas()!=null) {
            medicoTabla.setCitas(medico.getCitas());
        }
        medicoRepository.save(medicoTabla);
        return mapper.map(medicoTabla, MedicoDTO.class);
    }
}
