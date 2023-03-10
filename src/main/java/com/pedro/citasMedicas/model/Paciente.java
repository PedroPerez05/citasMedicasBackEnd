package com.pedro.citasMedicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "Paciente")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) //Al hacer @Data nos esta sobrescribiendo el metodo ToString
//por lo que debemos llamar al de la clase padre para que tambien lo implemente
public class Paciente extends Usuario{
    @Column(name = "NSS")
    private String NSS;
    @Column (name = "numTarjeta")
    private String numTarjeta;
    @Column (name = "telefono")
    private String telefono;
    @Column (name = "direccion")
    private String direccion;
    @ManyToMany //N -> M
    private List<Medico> medicos;

    @OneToMany (mappedBy = "paciente") //en el mapeo debe ponerse el nombre del atributo de paciente que hay en la clase "Cita"
    private List<Cita> citas;
}
