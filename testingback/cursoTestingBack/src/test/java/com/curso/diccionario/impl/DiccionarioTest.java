package com.curso.diccionario.impl;

import com.curso.diccionario.Diccionario;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DiccionarioTest {

    private static Diccionario diccionario;
    // Escenario: Preguntar por una palabra que existe
    @BeforeClass // Antes de hacer todas las pruebas... ejecuta este código
    public static void cargarDiccionario(){
        DiccionarioTest.diccionario = new SuministradorDeDiccionariosImpl().getDiccionario("ES").get();
    }
    //@AfterClass // Después de hacer todas las pruebas... ejecuta este código
    //@Before     // Antes de CADA prueba... ejecuta este código
    //@After      // Después de CADA prueba... ejecuta este código

    @Test
    public void preguntarPorPalabraQueExiste() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   pregunto por la palabra "manzana"
        boolean respuesta = diccionario.existe("manzana");
        // Entonces me responde que si existe
        assertTrue(respuesta);
    }

    @Test
    // Escenario: Preguntar por una palabra que no existe
    public void preguntarPorPalabraQueNoExiste() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   pregunto por la palabra "archilococo"
        boolean respuesta = diccionario.existe("archilococo");
        // Entonces me responde que no existe
        assertFalse(respuesta);
    }

    @Test
    // Escenario: Recuperar los significados de una palabra que existe
    public void significadosDeUnaPalabraQueExisteConUnSignificado() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   solicito los significados de la palabra "manzana"
        Optional<List<String>> respuesta = diccionario.getSignificados("manzana");
        // Entonces me devuelve 1 significado
        assertTrue(respuesta.isPresent());
        assertEquals(1, respuesta.get().size());
        // Y        el significado número 1 es "Fruto del manzano"
        assertEquals("Fruto del manzano", respuesta.get().get(0));
    }

    @Test
    // Escenario: Recuperar los significados de una palabra que existe
    public void significadosDeOtraPalabraQueExisteConMuchosSignificados() {
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
    // Escenario: Recuperar los significados de una palabra que no existe
    public void recuperarSignificadosDeUnaPalabraQueNoExiste() {
        // Dado     que tengo un diccionario de idioma "ES"
        // Cuando   solicito los significados de la palabra "archilococo"
        Optional<List<String>> respuesta = diccionario.getSignificados("archilococo");
        // Entonces no me devuelve nada}
        assertFalse(respuesta.isPresent());
    }
}