package com.pedro.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaDTO {
    private Long id;
    private Date fechaHora;
    private String motivoCita;

    private DiagnosticoDTOSimp diagnostico;

    private MedicoDTOSimpC medico;
    private PacienteDTOSimpC paciente;

    //private Diagnostico diagnosticoSimp;

    /*public static CitaDTO convertirCitaADTO(Cita cita) {  //Prueba
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(cita.getId());
        citaDTO.setFechaHora(cita.getFechaHora());
        citaDTO.setMotivoCita(cita.getMotivoCita());
        return citaDTO;
    }*/
}
