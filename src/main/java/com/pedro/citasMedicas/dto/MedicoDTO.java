package com.pedro.citasMedicas.dto;

import com.pedro.citasMedicas.model.Cita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private String numColegiado;
    private List<Cita> citas;

}
