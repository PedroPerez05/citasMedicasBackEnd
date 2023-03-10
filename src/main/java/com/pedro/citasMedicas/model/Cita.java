package com.pedro.citasMedicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table (name = "Cita", uniqueConstraints = @UniqueConstraint(columnNames = { "diagnostico_id" }))

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "fechaHora")
    private Date fechaHora;
    @Column (name = "motivoCita")
    private String motivoCita;

    @OneToOne (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "diagnostico_id")
    private Diagnostico diagnostico;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
