package com.pedro.citasMedicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Diagnostico")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column (name = "valoracionEspecialista")
    private String valoracionEspecialista;
    @Column (name = "enfermedad")
    private String enfermedad;
}
