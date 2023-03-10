package com.pedro.citasMedicas.dto;

import com.pedro.citasMedicas.model.Cita;
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

    /*public static CitaDTO convertirCitaADTO(Cita cita) {  //Prueba
        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setId(cita.getId());
        citaDTO.setFechaHora(cita.getFechaHora());
        citaDTO.setMotivoCita(cita.getMotivoCita());
        return citaDTO;
    }*/
}
