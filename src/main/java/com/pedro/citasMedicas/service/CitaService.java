package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.dto.CitaDTO;
import com.pedro.citasMedicas.dto.DiagnosticoDTOSimp;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Diagnostico;
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
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper = new ModelMapper();

    public List<CitaDTO> obtenerCita() {
        List<CitaDTO> citasDTO = new ArrayList<CitaDTO>();
        List<Cita> citas = citaRepository.findAll();
        for (Cita cita : citas) {
            citasDTO.add(mapper.map(cita, CitaDTO.class)); //mapeamos para evitar el bucle
        }
        return citasDTO;
    }

    @Transactional
    public CitaDTO insertarCita(Cita cita) { //cita debe tener si o si, un medico, un paciente, y un diagnostico
        diagnosticoRepository.save(cita.getDiagnostico());
        ArrayList<Medico> medico=new ArrayList<>();
        if(cita.getMedico().getClave()!=null) { //no debe ser null, pero es para que no me salte error
            cita.getMedico().setClave(passwordEncoder.encode(cita.getMedico().getClave()));
        }
        medicoRepository.save(cita.getMedico());
        if(cita.getPaciente().getClave()!=null) { //no debe ser null, pero es para que no me salte error
            cita.getPaciente().setClave(passwordEncoder.encode(cita.getPaciente().getClave()));
        }
        medico.add(cita.getMedico()); //hacemos esto para que se establezca la relacion entre el medico y el paciente
        cita.getPaciente().setMedicos(medico);
        pacienteRepository.save(cita.getPaciente());
        citaRepository.save(cita);
        return mapper.map(cita, CitaDTO.class);
    }

    public String eliminarCita(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return "Cita eliminada correctamente";
        } else {
            return "Cita no encontrado";
        }
    }

    public CitaDTO actualizarCita(Cita cita, Long id) {
        if(citaRepository.existsById(id)){
        Cita citaTabla = citaRepository.findById(id).orElse(null);
        if (cita.getDiagnostico() != null) {
            Diagnostico d = diagnosticoRepository.findById(citaTabla.getDiagnostico().getId()).orElse(null);
            if (cita.getDiagnostico().getEnfermedad() != null) {
                d.setEnfermedad(cita.getDiagnostico().getEnfermedad());
            }
            if (cita.getDiagnostico().getValoracionEspecialista() != null) {

                d.setValoracionEspecialista(cita.getDiagnostico().getValoracionEspecialista());
            }
            diagnosticoRepository.save(d);
        }
        if (cita.getMedico() != null) {
            if(cita.getMedico().getId()==null&&medicoRepository.existsById(citaTabla.getMedico().getId())) { //si no existe,creamos un medico

                Medico m=medicoRepository.findById(citaTabla.getMedico().getId()).orElse(null);
                m.setUsuario(cita.getMedico().getUsuario());
                m.setNumColegiado(cita.getMedico().getNumColegiado());
                medicoRepository.save(m); //no hace falta hacer setMedico porque este medico ya esta relacionado con la cita
            }else{
                Medico m = new Medico();
                //Solo estos parametros para estar probando
                //m.setId(cita.getMedico().getId()); No me deja poner el id que quiera porque se genera automaticamente, por lo tanto si
                //en actualizar le pasamos un id a medico, no generara uno nuevo
                m.setUsuario(cita.getMedico().getUsuario());
                m.setNumColegiado(cita.getMedico().getNumColegiado());
                medicoRepository.save(m);
                citaTabla.setMedico(m);
            }
        }
        if (cita.getPaciente() != null) {
            if(cita.getPaciente().getId()==null&&pacienteRepository.existsById(citaTabla.getPaciente().getId())) { //si no existe,creamos un medico

                Paciente p=pacienteRepository.findById(citaTabla.getPaciente().getId()).orElse(null);
                p.setUsuario(cita.getPaciente().getUsuario());
                p.setTelefono(cita.getPaciente().getTelefono());
                pacienteRepository.save(p); //no hace falta hacer setMedico porque este medico ya esta relacionado con la cita
            }else{
                Paciente p = new Paciente();
                //Solo estos parametros para estar probando
                //m.setId(cita.getMedico().getId()); No me deja poner el id que quiera porque se genera automaticamente, por lo tanto si
                //en actualizar le pasamos un id a medico, no generara uno nuevo
                p.setUsuario(cita.getPaciente().getUsuario());
                p.setTelefono(cita.getPaciente().getTelefono());
                pacienteRepository.save(p);
                citaTabla.setPaciente(p);
            }
        }
        if(cita.getMotivoCita()!=null) {
            citaTabla.setMotivoCita(cita.getMotivoCita());
        }
        if (cita.getFechaHora()!=null) {
            citaTabla.setFechaHora(cita.getFechaHora());
        }
        citaRepository.save(citaTabla);
        return mapper.map(citaTabla, CitaDTO.class);
        }else{
            return null; //cita no existe
        }
    }
        public CitaDTO verCita (Long id){
            return (mapper.map(citaRepository.findById(id).orElse(null), CitaDTO.class));
        }
}