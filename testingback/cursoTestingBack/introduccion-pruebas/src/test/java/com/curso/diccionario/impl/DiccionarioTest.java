package com.curso.diccionario.impl;

import com.curso.diccionario.Diccionario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
//import org.junit.BeforeClass;
//import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.*;

class DiccionarioTest {

    private static Diccionario diccionario;
    // Escenario: Preguntar por una palabra que existe
    //@BeforeClass // Antes de hacer todas las pruebas... ejecuta este código           JUNIT4
    @BeforeAll  // Igual que BeforeClass... pero en JUNIT5
    static void cargarDiccionario(){
        DiccionarioTest.diccionario = new SuministradorDeDiccionariosImpl().getDiccionario("ES").get();
    }
    //@AfterClass // Después de hacer todas las pruebas... ejecuta este código          JUNIT4
    //@AfterAll
    //@Before     // Antes de CADA prueba... ejecuta este código                        JUNIT4
    //@After      // Después de CADA prueba... ejecuta este código                      JUNIT4
    //@BeforeEach                                                                       JUNIT5
    //@AfterEach                                                                        JUNIT5

    @Test
    @DisplayName("Preguntar por una palabra que existe")
    void preguntarPorPalabraQueExiste() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   pregunto por la palabra "manzana"
        boolean respuesta = diccionario.existe("manzana");
        // Entonces me responde que si existe
        assertTrue(respuesta);
    }

    @ParameterizedTest // Es de la librería params de JUNIT
    @ValueSource(strings = {"archilococo", "estringolococo"})
    @DisplayName("Preguntar por una palabra que no existe")
    // Escenario: Preguntar por una palabra que no existe
    void preguntarPorPalabraQueNoExiste( String palabra ) {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   pregunto por la palabra "archilococo"
        boolean respuesta = diccionario.existe(palabra);
        // Entonces me responde que no existe
        assertFalse(respuesta);
    }

    @ParameterizedTest
    @DisplayName("Recuperar los significados de una palabra que existe")
    //@CsvSource({"manzana, 1, Fruto del manzano", "melón, 2, Fruto del melonero"})
    @CsvFileSource(resources = "/significados.csv", numLinesToSkip = 1)
    // Escenario: Recuperar los significados de una palabra que existe
    void significadosDeUnaPalabraQueExisteConUnSignificado(String palabra, int numeroDeSignificados, String primerSignificado, String segundoSignificado) {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   solicito los significados de la palabra "manzana"
        Optional<List<String>> respuesta = diccionario.getSignificados(palabra);
        // Entonces me devuelve 1 significado
        assertTrue(respuesta.isPresent());
        assertEquals(numeroDeSignificados, respuesta.get().size());
        // Y        el significado número 1 es "Fruto del manzano"
        assertEquals(primerSignificado, respuesta.get().get(0));
        if(numeroDeSignificados>1)
            assertEquals(segundoSignificado, respuesta.get().get(1));
    }

    @Test
    @DisplayName("Recuperar los significados de una palabra que existe")
    // Escenario: Recuperar los significados de una palabra que existe
    void significadosDeOtraPalabraQueExisteConMuchosSignificados() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   solicito los significados de la palabra "melón"
        Optional<List<String>> respuesta = diccionario.getSignificados("melón");
        // Entonces me devuelve 2 significados
        assertTrue(respuesta.isPresent());
        assertEquals(2, respuesta.get().size());
        // Y        el significado número 1 es "Fruto del melonero"
        assertEquals("Fruto del melonero", respuesta.get().get(0));
        // Y        el significado número 2 es "Persona con pocas luces"
        assertEquals("Persona con pocas luces", respuesta.get().get(1));
    }

    @Test
    @DisplayName("Recuperar los significados de una palabra que no existe")
    // Escenario: Recuperar los significados de una palabra que no existe
    void recuperarSignificadosDeUnaPalabraQueNoExiste() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   solicito los significados de la palabra "archilococo"
        Optional<List<String>> respuesta = diccionario.getSignificados("archilococo");
        // Entonces no me devuelve nada}
        assertFalse(respuesta.isPresent());
    }
}