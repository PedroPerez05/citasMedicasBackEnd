package com.pedro.citasMedicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
//TRES TIPOS ->
// .SINGLE_TABLE (Crea una sola tabla con todos los campos de la clase padre e hijas)
// .JOINED (Crea una tabla de la superclase y una tabla por cada clase hija, las cuales derivan de la clase padre, recibiendo sus atributos)
// .TABLE_PER_CLASS (Crea una tabla para cade clase hija, con todos sus atributos, mas los atributos de la superclase)

/*-------Lombok-------*/
@Data //getter & setter & toString & etc
@AllArgsConstructor //constructor con todos los parametros
@NoArgsConstructor  //contructor por defecto
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; //Long es mas recomendado que Integer

    @Column (name = "nombre") //Nombre de la columna (No es obligatorio la anotacion @Colum)
    private String nombre;
    @Column (name = "apellidos")
    private String apellidos;
    @Column (name = "usuario")
    private String usuario;
    @Column (name = "clave")
    private String clave;
    @Column (name = "roles")
    private String roles;
}
