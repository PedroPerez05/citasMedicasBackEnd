package com.pedro.citasMedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTOSimp extends UsuarioDTO{
    private String NSS;
    private String numTarjeta;
    private String telefono;
    private String direccion;
    private List<CitaDTOSimp> citas;
}
