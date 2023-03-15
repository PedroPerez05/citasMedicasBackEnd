package com.pedro.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO extends UsuarioDTO{
    private String numColegiado;
    private List<CitaDTOSimp> citas;

   private List<PacienteDTOSimp> pacientes;

}
