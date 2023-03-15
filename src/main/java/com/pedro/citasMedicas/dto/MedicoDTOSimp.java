package com.pedro.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTOSimp extends UsuarioDTO{
    private String numColegiado;
    private List<CitaDTOSimp> citas;
}
