package com.curso.mascota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Le indicamos a JUNIT, que Algunos valores de los de esta clase, pueden ser suministrados por Spring
// Habilito la inyección de dependencias de Spring en JUNIT
@ExtendWith(SpringExtension.class)
// Para hacer estas pruebas, necesito arrancar esa aplicación!
@SpringBootTest(classes = AplicacionDePruebas.class)
// Spring, arranca esa aplicación que he montado en la clase AplicacionDePruebas, para que yo pueda ejecutar estas pruebas.
class MascotasTest {

    // Que spring se encargue de darme ese repositorio de mascotas...
    // // que de hecho incluso me va a escribir una clase que implemente mi interfaz en autom.
    // @Autowired. Está considerado mala práctica... y además tiene una limitación:
    // Limitación: Se entrega el valor después de llamar al constructor... y si lo necesito en el constructor... voy jodido... no lo tengo todavía.
    // Mala práctica: Esto usa por debajo REFLECTIONS: Esto es un problema de SEGURIDAD... y además, LENTO de narices
    private final MascotaRepository repositorioDeMascotas;

    public MascotasTest(@Autowired MascotaRepository repositorioDeMascotas){ // Inyección de dependencias
                                                                             // La uso para no depender de una implementación concreta de la interfaz
                                                                             // Para respetar el principio de inversión de dependencias
        this.repositorioDeMascotas = repositorioDeMascotas;
    }
    @Test
    @DisplayName("Crear una mascota sin nombre")
    void mascotaSinNombre(){
        // Dado
        // Que tengo un repositorio de Mascotas
        // Y Que tengo una mascota... con todos sus datos... menos el nombre
        Mascota miMascota = new Mascota();
        miMascota.setEdad(3);
        // Cuando: Se solicite su almacenamiento en BBDD
        //asegurarQueSeLanzaException( () -> repositorioDeMascotas.save(miMascota) );
        Assertions.assertThrows(Exception.class, () -> repositorioDeMascotas.save(miMascota) );
    }
/*
    private void asegurarQueSeLanzaException(Supplier<Mascota> funcion ){
        try {
            // Algo que debe ejecutarse
            funcion.get();
        }catch(Exception e){
            return;
        }
        // Entonces: OSTION PADRE DE LA HUERTA
        Assertions.fail("Se debería haber dado una exception al ejecutar esa función.");
    }
*/

    @Test
    @DisplayName("Crear una mascota sin edad")
    void mascotaSinEdad(){
        // Dado
        // Que tengo un repositorio de Mascotas
        // Que tengo una mascota... con todos sus datos... menos la edad
        Mascota miMascota = Mascota.builder().nombre("Firulais").build();
        // Cuando: Se solicite su almacenamiento en BBDD
        // Entonces: Se guarda guay
        Assertions.assertDoesNotThrow( () -> repositorioDeMascotas.save(miMascota) );
    }
    @Test
    @DisplayName("Crear una mascota con todos los datos")
    void mascotaGuay(){
        // Dado
        // Que tengo un repositorio de Mascotas
        // Que tengo una mascota... con todos sus datos...
        Mascota miMascota = Mascota.builder().nombre("Firulais").edad(5).build();
        // Cuando: Se solicite su almacenamiento en BBDD
        // Entonces: Se guarda guay
        Assertions.assertDoesNotThrow( () -> repositorioDeMascotas.save(miMascota) );
    }

    @Test
    @DisplayName("Que se genera un ID")
    void seGeneraId(){
        // Dado
        // Que tengo un repositorio de Mascotas
        // Que tengo una mascota... con todos sus datos...
        Mascota miMascota = Mascota.builder().nombre("Firulais").edad(5).build();
        // Cuando: Se solicite su almacenamiento en BBDD
        // Entonces: Se guarda guay
        Mascota mascotaGuardada = repositorioDeMascotas.save(miMascota);
        Assertions.assertNotNull(mascotaGuardada.getId());
        Assertions.assertTrue(mascotaGuardada.getId()>=0);
    }
}
