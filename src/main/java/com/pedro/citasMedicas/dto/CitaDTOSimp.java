package com.pedro.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CitaDTOSimp {
    private Long id;
    private Date fechaHora;
    private String motivoCita;

    private DiagnosticoDTOSimp diagnostico;
}
