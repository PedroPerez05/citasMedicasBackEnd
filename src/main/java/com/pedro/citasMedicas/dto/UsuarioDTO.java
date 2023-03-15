package com.pedro.citasMedicas.dto;

import com.pedro.citasMedicas.model.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String clave;

    private String roles;
}
