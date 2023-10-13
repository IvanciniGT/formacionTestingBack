package com.curso.mascota;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    // En automático, SPRING (y aquí empieza la magia), va a crear una implementación de esta interfaz.
    // Heredados de la interfaz JpaRepository tenemos ya la reostia de métodos definidos:
    // findAll, findById, save, delete, ...
    // Spring (a través de Hibernate) va a darme código para todos esos métodos...
    // Es más, ese código se generará para la BBDD concreta que yo decida usar en cada entorno. DESARROLLO: MySQL
    //                                                                                          Producción: Oracle
}
