package com.pedro.citasMedicas.dto;

import com.pedro.citasMedicas.model.Cita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private String NSS;
    private String numTarjeta;
    private String telefono;
    private String direccion;
    private List<Cita> citas;
}
