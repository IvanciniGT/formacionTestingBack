package com.curso.mascota;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

// Vamos a trabajar con JPA <- JEE
@Entity // Spring, Quiero poder persistir objetos de este tipo en una bbdd
@Table(name = "mascotas")
// Lombok
@Data                   // Getter, setters, y to string bonito ... equals, hasCode
@Builder                // Patrón builder para crear objetos de este tipo
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    @Id // Indica que es la clave primaria
    @GeneratedValue(strategy= GenerationType.AUTO) // El valor quiero que se genere en AUTOM por la BBDD
    private Long id;

    @Column(nullable = false, length = 50)     // NOMBRE VARCHAR(50) NOT NULL,
    @NotBlank(message = "El nombre de la mascota es obligatorio")
    private String nombre;

    @Column(nullable = true)
    private Integer edad;
}
