package com.curso.diccionario.impl;

import com.curso.diccionario.Diccionario;
import com.curso.diccionario.SuministradorDeDiccionarios;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//import org.junit.Test;
//import static org.junit.Assert.*;

class SuministradorDeDiccionarioTest {

    @Test
    @DisplayName("Preguntar por un diccionario que existe")
    void preguntarPorDiccionarioQueExiste(){
        // Dado que tengo un suministrador
        SuministradorDeDiccionarios suministradorDeDiccionarios = new SuministradorDeDiccionariosImpl();
        // Y Que tenga idioma ES... Ya he creado un fichero según debe funcionar la implementación
        //Cuando      pregunto al suministrador si tiene un diccionario del idioma "ES"
        boolean respuesta = suministradorDeDiccionarios.tienesDiccionarioDe("ES");
        //Entonces    me responda que sí.
        assertTrue(respuesta);
    }
    @Test
    @DisplayName("Preguntar por un diccionario que no existe")
    void preguntarPorDiccionarioQueNoExiste(){
        // Dado que tengo un suministrador
        SuministradorDeDiccionarios suministradorDeDiccionarios = new SuministradorDeDiccionariosImpl();
        // Y           que el suministrador de diccionario no tiene un diccionario para idioma "DE LOS ELFOS"
        //Cuando      pregunto al suministrador si tiene un diccionario del idioma "ES"
        boolean respuesta = suministradorDeDiccionarios.tienesDiccionarioDe("DE LOS ELFOS");
        //Entonces    me responda que sí.
        assertFalse(respuesta);
    }

    @Test
    @DisplayName("Solicitar un diccionario que existe")
    void solicitarUnDiccionarioQueExiste(){
        // Dado que tengo un suministrador
        SuministradorDeDiccionarios suministradorDeDiccionarios = new SuministradorDeDiccionariosImpl();
        // Y Que tenga idioma ES... Ya he creado un fichero según debe funcionar la implementación
        // Cuando      solitito al suministrador un diccionario del idioma "ES"
        Optional<Diccionario> diccionario = suministradorDeDiccionarios.getDiccionario("ES");
        // Entonces    me devuelve un diccionario
        assertTrue(diccionario.isPresent());
        // Y           ese diccionario debe ser un diccionario de idioma "ES"
        assertEquals("ES", diccionario.get().getIdioma());
    }
    @Test
    @DisplayName("Solicitar un diccionario que no existe")
    void solicitarUnDiccionarioQueNoExiste(){
        // Dado que tengo un suministrador
        SuministradorDeDiccionarios suministradorDeDiccionarios = new SuministradorDeDiccionariosImpl();
        // Y Que tenga idioma ES... Ya he creado un fichero según debe funcionar la implementación
        // Cuando      solitito al suministrador un diccionario del idioma "DELOSELFOS"
        Optional<Diccionario> diccionario = suministradorDeDiccionarios.getDiccionario("DE LOS ELFOS");
        //     Entonces    no me devuelve nada.
        assertFalse(diccionario.isPresent());
    }
}

