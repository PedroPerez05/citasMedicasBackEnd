package com.pedro.citasMedicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table (name = "Medico")

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Medico extends Usuario{
    @Column (name = "numColegiado")
    private String numColegiado;
    @ManyToMany (mappedBy = "medicos")
    private List<Paciente> pacientes;

    @OneToMany (mappedBy = "medico")
    private List<Cita> citas;
}
