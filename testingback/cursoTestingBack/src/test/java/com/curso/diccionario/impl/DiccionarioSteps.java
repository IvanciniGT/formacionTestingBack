package com.curso.diccionario.impl;

import com.curso.diccionario.Diccionario;
import com.curso.diccionario.SuministradorDeDiccionarios;
import io.cucumber.java.en.Given;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class DiccionarioSteps {
    private SuministradorDeDiccionarios miSuministrador;
    private boolean respuesta;
    private Optional<Diccionario> diccionario;

    @Given("Que tenga un suministrador de diccionarios")
    public void queTengaUnSuministradorDeDiccionarios() {
        miSuministrador = new SuministradorDeDiccionariosImpl();
    }

    @Dado("que el suministrador de diccionario tiene un diccionario para idioma {string}")
    public void queElSuministradorDeDiccionarioTieneUnDiccionarioParaIdioma(String idioma) {
        // No voy a hacer anda... Tendré un fichero para ese idioma!
    }

    @Cuando("pregunto al suministrador si tiene un diccionario del idioma {string}")
    public void preguntoAlSuministradorSiTieneUnDiccionarioDelIdioma(String idioma) {
        respuesta = miSuministrador.tienesDiccionarioDe(idioma);
    }

    @Entonces("me responda que sí.")
    public void meRespondaQueSí() {
        Assertions.assertTrue(respuesta);
    }

    @Y("que el suministrador de diccionario no tiene un diccionario para idioma {string}")
    public void queElSuministradorDeDiccionarioNoTieneUnDiccionarioParaIdioma(String idioma) {
        // Pues será que no hay fichero
    }

    @Entonces("me responda que no.")
    public void meRespondaQueNo() {
        Assertions.assertFalse(respuesta);
    }

    @Cuando("solicito al suministrador un diccionario del idioma {string}")
    public void solicitoAlSuministradorUnDiccionarioDelIdioma(String idioma) {
        diccionario = miSuministrador.getDiccionario(idioma);
    }

    @Entonces("me devuelve un diccionario")
    public void meDevuelveUnDiccionario() {
        Assertions.assertTrue(diccionario.isPresent());
    }

    @Y("ese diccionario debe ser un diccionario de idioma {string}")
    public void eseDiccionarioDebeSerUnDiccionarioDeIdioma(String idioma) {
        Assertions.assertEquals(idioma, diccionario.get().getIdioma());
    }

    @Entonces("no me devuelve ningún diccionario.")
    public void noMeDevuelveNingunDiccionario() {
        Assertions.assertFalse(diccionario.isPresent());
    }

    @Dado("que tengo un diccionario de idioma {string}")
    public void queTengoUnDiccionarioDeIdioma(String arg0) {
    }

    @Cuando("pregunto por la palabra {string}")
    public void preguntoPorLaPalabra(String arg0) {
    }

    @Entonces("me responde que si existe")
    public void meRespondeQueSiExiste() {
    }

    @Entonces("me responde que no existe")
    public void meRespondeQueNoExiste() {
    }

    @Cuando("solicito los significados de la palabra {string}")
    public void solicitoLosSignificadosDeLaPalabra(String arg0) {
    }

    @Entonces("me devuelve {int} significado")
    public void meDevuelveSignificado(int arg0) {
    }

    @Y("el significado número {int} es {string}")
    public void elSignificadoNúmeroEs(int arg0, String arg1) {
    }

    @Entonces("me devuelve {int} significados")
    public void meDevuelveSignificados(int arg0) {
    }

    @Entonces("no me devuelve nada")
    public void noMeDevuelveNada() {
    }
}
