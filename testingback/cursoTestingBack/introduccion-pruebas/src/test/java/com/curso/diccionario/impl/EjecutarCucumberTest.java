package com.curso.diccionario.impl;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

// Le indicamos a JUnit que esto son pruebas de a través de una herramienta externa
@Suite                           // junit-platform-suite
// Le indicamos con qué herramienta queremos ejecutar las pruebas
@IncludeEngines("cucumber")      // junit-platform-engine    "cucumber" -> cucumber-junit-platform-engine
// Le indicamos a JUNIT que le indique a cucumber que las pruebas están definidas en los archivos de la carpeta features
@SelectClasspathResource("features")
// Le indicamos a JUNIT que le indique a cucumber en que paquete están los ficheros de steps
@ConfigurationParameter( key = GLUE_PROPERTY_NAME, value = "com.curso.diccionario.impl")

// Si estoy con JUnit 4
//  @RunWith(Cucumber.class)
//  @CucumberOptions(
//          features = "classpath:features",
//          glue = "com.curso.diccionario.impl",
//          plugin = {"pretty", "html:target/cucumber-reports"}
//  )
public class EjecutarCucumberTest {
}
