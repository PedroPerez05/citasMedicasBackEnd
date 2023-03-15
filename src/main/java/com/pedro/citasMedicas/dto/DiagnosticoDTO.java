package com.pedro.citasMedicas.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DiagnosticoDTO {
    private Long id;
    private String valoracionEspecialista;
    private String enfermedad;

    private CitaDTOSimp cita;
}
