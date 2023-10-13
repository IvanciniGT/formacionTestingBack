package com.curso.mascota;

import org.junit.jupiter.api.Test;

import java.util.function.*;

class ProgramacionFuncionalTest {

    @Test
    void ejemploProgramacionFuncional(){
        String miTexto = "HOLA"; // Statement
        int numero = 5+6;        // Statement
                     /// Expresión: Un trozo de código que devuelve algo
        Function<Integer, Integer> miFuncion = this::doble;  // Operador :: que permite referenciar funciones
        System.out.println( miFuncion.apply(5) );
        imprimirResultadoDeOperacion(miFuncion, 7);
        imprimirResultadoDeOperacion(this::doble, 10);
        // En java 1.8, aparece un segundo operador: El operador -> que permite definir expresiones LAMBDA
        // Una expresión lambda es un trozo de código que devuelve una función ANONIMA, creada dentro del statement
        Function<Integer, Integer> miFuncion2 = (Integer numero2) ->{
            return numero2*3;
        };
        imprimirResultadoDeOperacion(miFuncion2, 7);
        imprimirResultadoDeOperacion(this::triple, 7);
        imprimirResultadoDeOperacion((Integer numero2) ->{
                                                            return numero2*3;
                                                        }, 7);
        imprimirResultadoDeOperacion((numero2) ->{
                                                            return numero2*3;
                                                        }, 7);
        imprimirResultadoDeOperacion(numero2 ->{
                                                            return numero2*3;
                                                        }, 7);
        imprimirResultadoDeOperacion((numero2) -> numero2*3, 7);
    }

    void imprimirResultadoDeOperacion(Function<Integer, Integer> operacion, int numero){
        System.out.println( operacion.apply(numero) );
    }

    public int doble(int numero){
        return numero*2;
    }
    int triple(int numero){
        return numero*3;
    }

}
