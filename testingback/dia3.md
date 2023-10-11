# Niveles de prueba

Esto tiene sentido desde mi perspectiva (YO , OH , CREADOR DEL TREN)
Sistema: TREN
    Componentes de mi sistema
        motor                   <- Prueba unitaria... Dejo al motor aislado de otros componentes de mi aplicación
        ruedas                  <- Prueba unitaria  \
        sistema de frenado      <- Prueba unitaria  / Prueba de integración

Desde el punto de vista del YO OH CREADOR DEL MOTOR

La prueba unitaria del motor, qué tipo de prueba es? DE SISTEMA
El motor por dentro tiene: Eje, Cableado, images... mierdas...
Y probaré cada cosa de esas.. y las iré juntando y haré pruebas de integración entre ellas....


## Ejecutar desde JUnit test de Cucumber

Junit  JunitPlatformSuite           CucumberJunitPlatform              Cucumber

## Dependencias en maven

En el pom, declaramos dependencias.
<dependencies>
    <dependency>
        <!--coordenadasdel artefacto del que dependemos-->
        <groupId></groupId>
        <artifactId></artifactId>
        <version></version>
        <!-- SCOPE: Para qué necesito esa dependencia-->
        <scope></scope>
    </dependency>
    
</dependencies>

# SCOPES de dependencias

Para qué uso la dependencia:
- Si yo no pongo nada, tengo una dependencia que necesito en todo momento:
   - Para compilar, para hacer pruebas y para mi entorno de producción.
   - Y el punto es que si algún artefacto, en el futuro, depende de mi artefacto....
     quiero que al descargar maven mi dependencia, también descargue la dependencia de la que yo dependo.
     A -> YO -> B
- compile           Cuando solamente necesito la dependencia para compilar mi proyecto... 
                    pero no es una dependencia que necesito en el entorno de producción.
                    LOMBOK (Es una librería que SOLO nos ofrece ANOTACIONES, que usamos a la hora de compilar...)
                        @Getter, que genera en el fichero .class resultante de compilar una clase los getter que hayamos anotado.
- test              Solo uso esa dependencia para pruebas... Si en el futuro un componente depende del mio, nmo quiero que ese
                    componente descargue estas dependencias... ni yo las necesito en producción: JUNIT, MOCKITO, CUCUMBER
- provided          Éste es similar a no poner nada... salvo que no arrastro la dependencia a otros componentes que dependan del
                    mio. MAVEN la descargará para compilar, y hacer pruebas en mi proyecto... Y yo la necesito en producción... pero ahí me la darán... No la llevo yo.

---

# Para el próximo día:

Servicio CRUD REST para la gestión de Mascotas



    Lógica                                      Datos

## Modulo de gestión de persistencia
    MascotaRepository                           Mascota                                     UNITARIA, INTEGRACION
        Mascota guardarMascota(Mascota);


                vvvv

## Modulo servicio de gestión de mascotas
    ServicioDeEmails
    
                vvvv

                                                                                            UNITARIAS (MOCKS, INTEGRACION (MOCKS))
## Modulo servicio de gestión de mascotas -> Depende de: Modulo de gestión de persistencia y Modulo servicio de gestión de mascotas
    MascotaService                              DTO DatosMascota, DatosDeAltaDeMascota
        DatosMascota altaDeMascota(DatosDeAltaDeMascota);
                        guardarMascota
                        ServicioDeEmails.mandarEmail

                vvvv

## Modulo exposición por REST del servicio de gestión de mascotas
    MascotaController                           DTO DatosMascotaRest, DatosDeAltaDeMascotaRest
        POST /api/v1/mascota    JSON


Todo este proyecto va a ir montado como montamos este tipo de proyectos hoy en día: SPRINGBOOT
Spring tiene su propia librería para pruebas, montada por encima de JUNIT.
Que entre ocas cosas nos permitirá generar MOCKS en automático... de hecho también depende de MOCKITO

Meteremos también pruebas con cucumber

MAVER -> SUREFIRE -> JUNIT -> JUNIT PLATFORM -> CUCUMBER -> SPRING  (MOCKITO)