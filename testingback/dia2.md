
# DEV-->OPS?

Ni es un perfil... ni es una metodología... ni son una cuantas herramientas de software

Es una cultura, es una filosofía, es un movimiento en pro de LA AUTOMATIZACION, de todos los pasos entre el desarrollo y la operación de un software.

Antiguamente los proyectos los gestionábamos siguiendo una metodología tradicional(en cascada)

- Un proyecto (en la empresa) que era montar la v1 de un sistema... con unos requisitos cerrados.   -> PROYECTO 1 (met cascada)
- Otro proyecto para generar una v2 del sistema... con unos requisitos también cerrados.            -> PROYECTO 2 (met cascada)

Ahora... una cosa es un proyecto... y otra cosa es cómo entiendo el ciclo de vida del producto que estoy desarrollando... el software
Y antiguamente entendíamos el ciclo de vida de un software: Software Development Management Cycle

Devops es una visión nueva de lo que era: Software Development Management Cycle
Que incluye la visión de lo que pasa una vez un software es construido y hay que operarlo...
Y me invita a automatizar todo lo que puedo automatizar en ese camino:

                                                                                        Automatizable?                  Herramientas?
PLAN        Requisitos... analizarlos e irlos asignado en el tiempo/personas            poco
CODE        Desarrollo... picar código como benditos                                    ummmm.... digamos que poco
                El código se deposita en un Repo de un SCM
BUILD       Empaquetado de la app... Compilación, transpilación,                        MUY AUTOMATIZABLE
            crearé un archivo unificado. Junto todas las piezas.
                                                                                                                        JAVA: Maven, Gradle, SBT
                                                                                                                        JS:   NPM, YARN, WEBPACK
                                                                                                                        .net: MsBuild, Dotnet, nuget
                                                                                                                        -----------------------------
                                                                                                                        Contenedores (Docker) / NADA QUE PONGA KUBERNETES
------------------ He conseguido automatizar hasta este punto ----------> Desarrollo ágil
TEST        Definición de las pruebas                                                   poco
            Codificar las pruebas (si las automatizamos)                                ummmm.... digamos que poco
            Ejecutar las pruebas                                                        MUY AUTOMATIZABLE
                                                                                                                        Frameworks de pruebas: JUnit, TestNG, MsTest, UnitTest, Mocha
                                                                                                                        Frameworks de Test-Doubles: Mockito, Sinon...
                                                                                                                        JACOCO: Informes de Cobertura
                                                                                                                        Rendimiento: JMeter, LoadRunner
                                                                                                                        Servicios Web (Backend): Postman, SoapUI, ReadyAPI, Karate...
                                                                                                                        Cucumber: Otro framework de pruebas... se suele usar para pruebas de sistema... simplemente porque los testers NO SON TONTOS (como los del mediamarkt)
                                                                                                                        UI:
                                                                                                                            - WEB: Selenium, Karma, Webdriver.io
                                                                                                                            - App Teléfono móvil: Appium
                                                                                                                            Katalon 
                                                                                                                            - Desktop: UFT 
                                                                                                                        Sonar 
                Las pruebas las ejecuto en la máquina del desarrollador?    NO... está maleao... no me fio
                Las pruebas las ejecuto en la máquina del tester?           NO... está maleao... no me fio
                Las pruebas la ejecuto en un entorno especial CONTROLADO para hacer pruebas: Integración, pruebas, test, q&a, pre
------------------ He conseguido automatizar hasta este punto ----------> Integración Continua (El producto final de un pipeline de CI: Informe de PRUEBAS) 
RELEASE     Poner en manos de mi cliente una versión de mi producto (Artefacto)
            Dejarlo en un repo de artefactos (NEXUS, artifactory, maven central, docker hub.)
------------------ He conseguido automatizar hasta este punto ----------> Entrega Continua (El producto final es un artefacto en un repo de artefactos): Continuous Delivery
DEPLOY      Instalar el artefacto(release) en un entorno de producción
------------------ He conseguido automatizar hasta este punto ----------> Despliegue Continuo (El producto final es un artefacto en un repo de artefactos): Continuous Deployment
OPERATION   Ejecutarlo... pararlo... reiniciarlo... hacerle backups... 
            vaciar logs... mnto (libs nuevas... seguridad)
MONITOR     Extraer información del entorno de producción útil para...
            - La operación
            - Definir nuevos requisitos
            - Tener nuevos datos... con los que retroalimentar el desarrollo
              - Machine Learning
            - Business Intelligence

## Metodologías de desarrollo

REFACTORIZACIÓN Cambiar la estructura del código sin cambiar su comportamiento... en aras de facilitar el mnto.
TEST-FIRST      Diseño primero las pruebas y luego pico el código que necesito para poner las pruebas en verde.
TDD             Test Driven Development: Test-First (unitarias) + Refactorización
BDD             Behavior driven development: Comienzo definiendo las pruebas de sistema
ATDD            Acceptance test driven development: Comienzo definiendo las pruebas de aceptación

## Qué significa automatizar (el lavado de la ropa)?

Hacer/Construir/Diseñar una máquina (programa) que haga lo que antes hacíamos los humanos.
En nuestro caso... esto significa crear programas.

Automatizar unas pruebas (que es lo que hoy en día hacen los testers), es crear un programa que pruebe mi programa.
Automatizar una instalación es crear un programa que haga la instalación de mi programa.

## Si he conseguido automatizar TODAS esas tareas puntuales (empaquetado, 200 pruebas... subir un artefacto a un artifactory...)

Hay algo que me falte automatizar? Una automatización de segundo nivel....
Me falta orquestar y llamar automáticamente a esas automatizaciones

    MI PROGRAMA < PROGRAMAS QUE PRUEBAN MI PROGRAMA (JUNIT) < QUIEN LLAMA A ESTOS PROGRAMAS? YO ?????
    Quizás después de ejecutar las pruebas unitarias... quiero ejecutar las pruebas de integraciónY después quiero ejecutar el subir mi artefacto a un repo
    Y después quiero ejecutar la instalación de ese artefacto en el entorno de prod. = ORQUESTAR TAREAS

    JENKINS, y equivalentes (CloudBees, Azure devops-TFS, Github actions, Gitlab CI/CD, Bamboo, TeamCity, TravisCI)
        Definir nuevos programas (Pipelines)... de CI/CD
    Cuando tiene sentido usar Jenkins? Cuando tengo automatizaciones de primer nivel

## Perfil

Antiguamente no teníamos casi ni automatizaciones de primer nivel... como para plantearnos el tener automatizaciones de segundo nivel (JENKINS)

Hoy en día, nos hace falta una persona que configure el Jenkins o equivalente.
Y esa persona tiene que saber de un huevo de cosas.
- No tiene que configurar un maven... pero tiene que saber como hablar con maven
- Esa persona no tiene que saber de pruebas... pero tiene que saber como ejecutar pruebas con Junit, Selenium, Postman, JMeter
- Esa persona tiene que saber como trabajar con repos de artefactos
- Esa persona tiene que saber como trabajar con contenedores

En algún momento... nos saldrá que para adoptar una cultura devops, me hace falta un perfil nuevo que antes no necesitaba en las empresas... y que hay gente llamándole DEVOPS a ese perfil...
BIEN!... pero sabiendo a que llamo devops... como perfil... que cada empresa usa este término como le da la gana! MAL!!!!!

Al administrador de sistemas que hoy en día crea y configura infras en un cloud en lugar de on premisses -> DEVOPS... RUINA !!!!! Es un admin v2.0
Al desarrollador que hoy en día automatiza tareas con maven... no le llamo devops...
Al tester que hoy en día automatiza tareas con Selenium... postman... cucumber... no le llamo devops... es un tester v2

Lo que si que ahora necesito un tio nuevo para el jenkins.... pues le pongo nombre a ese perfil: DEVOPS !

# JUNIT

Framework para el desarrollo de PRUEBAS. PRUEBAS unitarias, de integración, de sistema... de rendimiento... de lo que quiera...

# MAVEN

Es una herramienta de automatización más... que automatiza las tareas habituales de un proyecto (normalmente JAVA)
- compilar
- compilar las pruebas
- ejecutar pruebas
- empaquetar
- Subir mi empquetado a un repo de artefactos
- Mandar mi código a sonar
- Generar un informe de cobertura de código
Para algunas de esas cosas... mi proyecto tiene dependencias que es necesario resolver... y maven también me ayuda con eso.
- Ni de coña es SOLO un gesto de dependencias

# Bitbucket

Servidor de alojamiento de repos remotos de git, igual que gitlab, github....
Git es el sistema de control de versiones que se usa en bitbucket... igual que en github o gitlab...

# Jacoco

Cobertura? Qué porcentaje de mi código está respaldado por pruebas unitarias... funcionen o no!
Esa información la ofrece? SonarQube... pero el sonar no la calcula... Sonar no ejecuta pruebas...
Sonar Recibe un informe JACOCO... que simplemente PUBLICA !

## Cómo los principios SOLID de desarrollo de software os van a ayudar con estas cosas....

Principios para hacer software fácilmente mantenible, básicamente desacoplando componentes.

S   Responsabilidad UNICA. Un objeto solo debe cumplir una responsabilidad... y por ende tener un único motivo para cambiar
        Mascota - Entidad                                                       Lógica de datos. lo que es una Mascota
        Repositorio de Mascotas - Persistencia (entidades: Mascota)             Lógica de persistencia
        Servicio de Mascotas                                                    Lógica de negocio
        Controlador REST de Mascotas                                            Lógica de exposición del servicio mediante protocolo REST

O   Abierto / Cerrado. Una clase debe estar abierta para su extensión pero cerrada para su modificación
L   Sustitución de Liskov: Cualquier objeto debe poder ser reemplazado por un subtipo de ese objeto sin que cambie eml comportamiento del sistema
I   Segregación de la interfaz: MEjor muchas interfaces específicas que una general
D   Principio de la inversión de dependencia *** IMPORTANTISIMO !
    Un módulo/componente de alto nivel de un sistema, no debe depender de implementaciones de un módulo/componente de más bajo nivel... solo de abstracciones (API, interfaz)

    Para respetar este principio, una técnica(patrón) de programación que podemos usar es el patrón de INYECCION DE DEPENDENCIAS

## Inyección de dependencias

Es un patrón de diseño de software por el cual una clase no crea instancias de los objetos que necesita... sino que le son suministrados.
Para qué? Para conseguir respetar el ppo de inversión de dependencias.

## Inversión de control

La inversión de control es otro patrón de diseño de software, por el cuál delegamos el control (y definición) del flujo de una app a un framework... 
a diferencia de el uso de técnicas más tradicionales de programación, en las que soy yo quien defino/controlo el flujo de mi app.
Para qué quiero esto? Para facilitarme usar un patrón de inyección de dependencias...

## Spring-CORE

Framework JAVA de inversión de control.

---

Aplicación de consola que permita a un usuario buscar una palabra en un diccionario de un determinado idioma

$ buscador ES manzana
Existe: Fruto del manzano

$ buscador ES archilococo
NO EXISTE

## Cómo lo planteo: Cuantos repos de git monto? 

### REPO 1: API Diccionario                                                             ---> 1 jar: diccionario-api.jar

package: com.diccionario

    public interface Diccionario{
        boolean existe(String palabra);
        Optional<List<String>> getSignificados(String palabra);         Esta función tiene complejidad ciclomática 2
                        //** VER NOTA 1
    }

    public interface SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma);
        Optional<Diccionario> getDiccionario(String idioma);
    }

### REPO 2a: Implementación de ese API... por ejemplo trabajando con ficheros           ---> 1 jar: diccionario-impl-ficheros.jar
package: com.diccionario.impl

    public class DiccionarioImpl implements Diccionario{
        boolean existe(String palabra){...}
        Optional<List<String>> getSignificados(String palabra){...}
    }

    public class SuministradorDeDiccionariosImpl implements SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma){...}
        Optional<Diccionario> getDiccionario(String idioma){...}
    }

### REPO 2b: Implementación de ese API... por ejemplo trabajando con bbdd
package: com.diccionario.impl.bbdd

    public class SuministradorDeDiccionariosDesdeBBDD implements SuministradorDeDiccionarios {
        boolean tienesDiccionarioDe(String idioma){...}
        Optional<Diccionario> getDiccionario(String idioma){...}
    }

### REPO 3: Frontal de consola                                                          ---> 1 jar: app-consola.jar

```java
package com.app;

    import com.diccionario.SuministradorDeDiccionarios;
    import com.diccionario.Diccionario;
    //import com.diccionario.impl.SuministradorDeDiccionariosImpl; // ESTA ES LA MUERTE DE UN PROYECTO ! LA ACABAMOS DE REGAR ENTERA !
        // CON ESTE IMPORT ME ACABO DE MEAR EN EL PRINCIPIO DE INVERSION DE DEPENDENCIA
    
    public class App {
        void procesarPetición(String palabra, String idioma, SuministradorDeDiccionarios miSuministrador){ // Inyección de dependencias
            // Haré cositas...
            boolean existeLaPalabra = false;
            //SuministradorDeDiccionarios miSuministrador = new SuministradorDeDiccionariosImpl(); // Crear una instancia de un Suministrador de Diccionarios
            if(miSuministrador.tienesDiccionarioDe(idioma)){
                Diccionario miDiccionario = miSuministrador.getDiccionario(idioma).get();
                existeLaPalabra = miDiccionario.existe(palabra);
            }
            // Haré cositas con esa variable que he rellenado
        }
    }
```

    APP -> DICCIONARIO
    ^^^
    Para hacerle una prueba unitaria, necesito AISLARLO del diccionario...
    Cómo hago eso: Por ejemplo generando un Test-Double del diccionario: Oye... 
    Diccionario de Mierda (mentirijilla que voy a crear)
        Si alguien te dice si existe la palabra ARCHILOCOCO -> FALSE
        Si alguien te dice si existe la palabra MANZANA -> TRUE... y su significado es Fruta del manzano

Spring es una librería que hace la INVERSION DE CONTROL
Es quién articula toda la lógica de mi aplicación

El punto es que lo que acabo de hacer ahí arriba es postponer el problema...
    En algún sitio sigo necesitando CREAR UNA INSTANCIA DE ESO: new SuministradorDeDiccionariosImpl();
Spring lo que hará, gracias a se él quién cre el flujo de mi programa es generar él esa instancia.

EL USO DE TEST-DOBLES que nosotros va emplear para pruebas unitarias y pruebas de sistema integración, simplifica ENORMEMENTE si aplico el principio de INVERSION DE DEPENDENCIAS
---
NOTA *1:
        - Siempre devuelve una lista de significados? Si la palabra no existe?
          - null
          - lista vacia
          - throw new NoSuchWordException
        No tengo ni idea de como se comporta esta función... Me toca mirar: DOCU o CODIGO DE LA IMPLEMENTACION
                                    archilococo... no existe: ERROR !!!!
    List<String> dameAlternativas(String palabra);                  Esta función tiene complejidad ciclomática 1
                                                                    Da igual si la palabra existe o no... o si encuentro palabras equivalentes o no.. está función siempre hará lo mismo:   
                                                                    Revisar todas las palabras del diccionario, a ver si alguna se parece a la que me pide.
                                                                        3765%%&&$$:) -> Ninguna
                                    archilococo Si no hay una lista vacia... está guay...

        manana
            manzana
            manzano
            mañana
            ananá

---

## Y cómo esos principios tiene su reflejo en el mundo del testing: FIRST

F   Fast                Rápido, que la prueba no tarde... entre otras cosas porque acabaré con 500 pruebas en mi proyecto
I   Isolated            Las pruebas deben ser independientes unas de otras.
R   Repeatable          La prueba debe poder ejecutarse tantas veces como quiera... sin que afecte a su resultado:
                            Cada prueba debe especificar claramente su GIVEN !... su estado de partida
S   Self-validating     La prueba debe contener todas las validaciones que sean necesarias para confirmarla o rechazarla: THEN
T   Timely              La necesito a tiempo... Oportuna. No me vale tener la prueba al final -> Unitarias -> Integración -> Sistema
    Thorough            Debe validar todos los caminos posibles 

---
# Estado del arte de JAVA

Herido de muerte! Lo único que mantiene a JAVA vivo es Spring.... y el legacy (desarrollos antiguos que no me planteo migrar a nuevas tecnologías).

En los 2000... Todo el mundo quería aprender JAVA.
Y JAVA era usado para todo:
- App web: JAVA
- App desktop: JAVA
- App Android: JAVA
- Software embebido: JAVA

Hoy en día:
- App web: 
  - Frontend: JS
  - Backend:  JAVA < SPRING (web... de otras cosas: ETLs... springbatch)
- App desktop: C#, C++, VB
- App Android: Kotlin
- Software embebido: C, GO, RUST

Por qué? qué ha pasado con JAVA? Resumiendo: 
- Tiene una sintaxis llena de cagadas, que no tengo en otros lenguajes y que hacen a JAVA incómodo de manejar
- Que lo compró ORACLE
  - MySQL  -> MariaDB
  - Hudson -> Jenkins
  - OpenOffice -> LibreOffice


# MAVEN
proyecto/
    src/    
        main/
            java/           Código java del proyecto
            resources/      Otros ficheros que necesita el proyecto (configuración...)
        test/
            java/           Código java de las pruebas de mi proyecto
            resources/      Otros ficheros que necesitan mis programs de prueba
    target/
        classes/
        test-classes/
        miproyecto.jar
    pom.xml         Contiene la configuración de mi proyecto para maven

## Goals de maven

Las tareas que puedo pedir a maven que haga sobre mi progyext
- compile           Compilar el código que tengo en main/java, generando unos archivos .class que se dejan en la carpeta target/classes/
    resources       Copiar los ficheros que tengo en main/resources a target/classes/
- test-compile      Compilar el código que tengo en test/java, generando unos archivos .class que se dejan en la carpeta target/test-classes/
    test-resources  Copiar los ficheros que tengo en test/resources a target/test-classes/
- test              Ejecuta mediante el plugin surefire los test que se encuentran en la carpeta    target/test-classes/
                    En paralelo, el plugin surefire genera un informe de pruebas (es lo que luego necesita Jenkins) en la carpeta target/surefire-reports/ 
- package           Empaqueta en un jar (zip) lo que hay en la carpeta target/classes/ y lo deja en la carpeta target/
- install           Copia el jar (realmente el resultado de la empaquetación) en la carpeta .m2 de mi máquina (mi repo de dependencias local)
- clean             Borra la carpeta target/

Hay goals adicionales: Algunos de esos goals se consiguen mediante la configuración de nuevos plugins
- integration-test
- sonar:sonar
- jacoco:report