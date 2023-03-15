package com.pedro.citasMedicas.service;

import com.pedro.citasMedicas.dto.MedicoDTO;
import com.pedro.citasMedicas.dto.PacienteDTO;
import com.pedro.citasMedicas.model.Cita;
import com.pedro.citasMedicas.model.Medico;
import com.pedro.citasMedicas.model.Paciente;
import com.pedro.citasMedicas.model.Usuario;
import com.pedro.citasMedicas.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper = new ModelMapper();
    public List<PacienteDTO> obtenerPacientes(){
        List<Paciente> pacientes=pacienteRepository.findAll();
        List<PacienteDTO> pacientesDTO=new ArrayList<PacienteDTO>();
        for (Paciente paciente: pacientes) {
            pacientesDTO.add(mapper.map(paciente, PacienteDTO.class));
        }
        return pacientesDTO;
    }
    public PacienteDTO verPaciente(Long id){
        return mapper.map(pacienteRepository.findById(id).orElse(null), PacienteDTO.class);
    }
    public PacienteDTO insertarPaciente(Paciente paciente){
        if(paciente.getMedicos()!=null){ //debo guardar primero el medico para que cuando inserte el paciente detecte al medico y se establezca la relacion en la tabla pacientes_medico
            for(Medico medico: paciente.getMedicos()){
                if(medico.getClave()!=null){
                medico.setClave(passwordEncoder.encode(medico.getClave()));
                }
                medicoRepository.save(medico);
            }
        }
        paciente.setClave(passwordEncoder.encode(paciente.getClave()));
        pacienteRepository.save(paciente);
        if(paciente.getCitas()!=null){
            PacienteDTO pacienteDTO=mapper.map(paciente, PacienteDTO.class);
            Paciente p1=mapper.map(pacienteDTO, Paciente.class);
            for(Cita cita: paciente.getCitas()){
                diagnosticoRepository.save(cita.getDiagnostico());
                cita.setPaciente(p1);
                citaRepository.save(cita);
            }
        }
        return mapper.map(paciente, PacienteDTO.class);
    }
    public String eliminarPaciente(Long id){
        int cont=0;
        Medico medico1=new Medico();
        if(pacienteRepository.existsById(id)) {
            System.out.println("dentro metodo");
            Paciente paciente=pacienteRepository.findById(id).orElse(null);
            if(!paciente.getMedicos().isEmpty()) { //hacemos esto porque un medico tiene que tener si o si un paciente, si este paciente es el ultimo con el que esta relacionado, lo borramos tambien
                for (Medico medico: paciente.getMedicos()){ //recorremos todos los medicos del paciente a eliminar
                    for(Paciente paciente1: medico.getPacientes()){ //recorremos si ese medico tiene mas de un paciente (si tiene uno, es el que vamos a eliminar, por lo tanto lo eliminamos)
                        cont++;
                    }
                    if(cont==1){ //si tiene solo su propio paciente, eliminamos el medico
                        medicoRepository.deleteById(medico.getId());
                    }
                }
                //PRUEBAS -> System.out.println("El contador es"+cont);
            }
            pacienteRepository.deleteById(id);
            return "Paciente borrado";
        }else{
            return "El paciente no existe";
        }
    }
    public PacienteDTO modificarPaciente(Paciente paciente, Long id){
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
        return mapper.map(pacienteTabla, PacienteDTO.class);
    }
}
