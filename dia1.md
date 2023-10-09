# Vacabulario en el mundo de las pruebas

- Error         Los humanos cometemos errores (por estar cansados, poco atentos, falta de conocimiento)
                Voy a hacer una mesa... y no estoy muy concentrado y me equivoco al medir la longitud de las patas... 
                La mesa tiene 4 patas... pero hago una de ellas más corta (5cms más corta)
- Defecto       Al cometer un error, introducimos un DEFECTO en un producto.
                Mi mesa tiene una pata más corta. 
- Fallo         y esos defectos en un momento dado, pueden manifestarse como fallos.
                EJECUTAR LA MESA: Lleno la mesa de platos y me siento a cenar.. y se va todo a tomar por culo !!!

# Para que sirven las pruebas?

- Asegurar que el código cumple los requerimientos / requisitos (funcionales/no funcionales) definidos.
- Intentar cazar el mayor número posible de fallos antes del paso a producción (ES NECESARIO EJECUTAR EL SOFTWARE)
    Una vez identificado un fallo, qué necesitamos corregir? El defecto que lo provoca
    Por tanto, lo primero que necesito es identificar el defecto -> Depuración o debugging (DESARROLLO)
- Otro objetivo de las pruebas es proveer información para la rápida identificación de defectos, desde los fallos que identificamos: *1
  - Recopilar toda la información posible... que facilite la identificación de defectos.
- En ocasiones, hacemos pruebas que identifiquen DEFECTOS directamente (para identificar directamente DEFECTOS, no es necesario ejecutar un programa)
  - Revisión del código... lo que antes hacía un desarrollador SENIOR ! Hoy en día esto lo hace SonarQube
    -> Identificar defectos
    -> Ayudar a tener un código con mayor calidad... y mejor mantenible 
        Sonar, en su informe, calcula:
            - Complejidad ciclomática de un código: El número de caminos que puede tomar el código a la hora de ejecutarse -> Número MINIMO de pruebas que he de realizar.
            - Complejidad cognitiva de un código:   Lo complejo que puede ser para un humano entender un código -> Mnto
- Para ver qué tal va el proyecto!
- Análisis de causas raíces.... de los defectos -> Identificar los ERRORES que estamos cometiendo... y sus causas
   ---> Definir acciones preventivas, que eviten nuevos ERRORES en en futuro... y por ende nuevos DEFECTOS y FALLOS

# Tipos de pruebas

Las pruebas las clasificamos en base a distintas taxonomías... paralelas entre sí:

Cualquier prueba debe centrarse en una CARACTERÍSTICA ÚNICA del sistema/componente que quiero probar.
De forma que si la prueba falla, rápidamente sea capaz de identificar dónde puede estar el problema (DEFECTO) *1

## En base al objeto de prueba

- Funcionales       Son las que guardan relación con requisitos funcionales
- No funcionales    Son las que guardan relación con requisitos NO FUNCIONALES
  - Carga
  - Estrés
  - Rendimiento
  - UX
  - HA

## En base al nivel de la prueba

- Unitarias                 Una prueba que se centra en una característica de un componente AISLADO del sistema!

                                Voy a fabricar un TREN!
                                Tendrá:
                                    MOTOR                                               Lo probaré... El motor por si solo. Le meto corriente y miro si gira... 
                                                                                                                                              y la potencia que entrega...
                                    RUEDAS                                              Las probaré... Las pego por ahí en algún lao... y le enchufo con la mano... giran?
                                    SISTEMA DE FRENOS                                   Le meto corriente y las pinzas se cierran?
                                    SISTEMA DE TRANSMISIÓN (Bielas.. engranajes....)

- De Integración            Una prueba que se centra el LA COMUNICACION de 2 componentes
                                    SISTEMA DE FRENOS -comunicación-> RUEDAS
                                    Y hago una prueba... Si le meto corriente a los frenos las rueda frena
- De sistema (end2end)      Se centran en el COMPORTAMIENTO del sistema en el conjunto.
                                    Pues ahora cojo el tren, lo pongo en marcha (BOTON ON!!!) y el tren sale hacia atrás !! A TOMAR POR CULO !!!!!
  -> De aceptación

Con qué problema me encuentro al hacer una prueba unitaria?
    Que tengo componentes en mi sistema (software) que dependen de otros componentes
        Quiero montar un servicio REST para la gestión de mascotas en una tienda (MASCOTAS FELIPE .com)

            - Un tipo de objeto llamado :                                                                                                   Mascota (public class Mascota{...})
            - Algo que me permita persistir las mascotas en una BBDD y recuperarlas/gestionarlas (CRUD):                                    MascotasRepository
            - Algo que me permita definir mi lógica de negocio... Alta de Mascota (persistencia... + envío de emails... + otras cosas...):  MascotasService
            - Algo que exponga esa lógica a través de un API REST:                                                                          MascotasRestControllerv1
            - Algo que exponga esa lógica a través de un API REST:                                                                          MascotasRestControllerv2
            - Algo que exponga esa lógica a través de un API SOAP:                                                                          MascotasSOAPController

        public class MascotasService{
            public DatosDeMascota altaDeMascota(DatosDeNuevaMascota datosDeNuevaMascota){
                // Validación de los datos de entrada
                // Persistencia                                --->    MascotasRepository
                // Mandar un email                             --->    EmailsService
            }
        }

        Quiero probar el altaDeMascota del MascotasService... pero éste componente se integra... (tiene dependencias) con otros componentes: MascotasRepository y EmailsService
        Por ende... si llamo directamente a esa función en una prueba... esto es lo que considero una PRUEBA UNITARIA? NO... o SI
                                                                                                  una prueba de Integración? NO o SI
                                                                                                  una prueba de sistema ? NO o SI !!!!

        Has aislado al componente MascotasService del resto de componentes? SI -> unitaria
                                                                            NO ?????
                                                                                Los has aislado de todos los demás componentes salvo de 1???? 
                                                                                    SI -> INTEGRACIÓN
                                                                                    NO -> Sistema

        En cualquier escenario, tendré una prueba FUNCIONAL!

### Caso 1: Pruebo la función altaDeMascota, trabajando contra el Repositorio REAL y el Servicio de emails REAL.
    
            public class MascotasService{
                public DatosDeMascota altaDeMascota(DatosDeNuevaMascota datosDeNuevaMascota){
                    // Validación de los datos de entrada
                    // Persistencia                                --->    MascotasRepository
                    // Mandar un email                             --->    EmailsService
                }
            }
            Si hay un fallo en la prueba, dónde está el fallo ^^^^ ?
            - Validación de los datos               (MascotasService)
            - Persistencia                          (MascotasRepository)
            - En el mandao del email                (EmailsService)
            - En la solicitud de mandar el email    (MascotasService - Comunicación con el EmailsService)
            - En la solicitud de persistencia       (MascotasService - Comunicación con el MascotasRepository)

    Si está prueba va bien, necesito hacer pruebas unitarias y de integración, para tener garantías de que el sistema se comporta como debe? NO
        Coño... no hace ya el sistema lo que debe? TODO GUAY
    Entonces... para que toda esta mierda Iván? 
        - Por si no va bien! Qué ha fallao? NPI ! Desmonta el tren!!!
          - Lo que ha fallado... está a muy bajo nivel... en el diseño del sistema... y me toca cambiar medio código... por no haberlo mirado antes!
        - Cuándo puedo hacer esta prueba? Cuando está todo el sistema montado? EN SERIO !!!!!!
          - Y mientras que hago? 

#### Tipo de prueba: de Sistema

Dado        los datos de una mascota (CORRECTOS)
            y... con el animalito que no exista ya... que sino la prueba me va a explotar
            y un Servicio de mascotas integrado con el servicio de emails y un repositorio (con su BBDD real por detrás (MYSQL))
Cuando      llamo a altaDeMascota pasándole los datos de la mascota(esos guays... que no existen todavía)
Entonces    La mascota queda dada de alta en la BBDD, con los datos ok (cómo compruebo esto? Consultando la BBDD)
            Se manda un email.... (cómo compruebo esto? Consultando la bandeja de entrada POP3 o IMAP de la cuenta de email correspondiente)
            La función devuelve los datos de la mascota tras persistirlos (que deben incluir un ID válido otorgado por el repositorio)

### Caso 2:  Pruebo la función altaDeMascota, trabajando contra un Repositorio de Mentirijilla y el Servicio de emails de Mentirijilla

            public class MascotasService{
                public DatosDeMascota altaDeMascota(DatosDeNuevaMascota datosDeNuevaMascota){
                    // Validación de los datos de entrada
                    // Persistencia                                - Intervenir estas comunicaciones-->    Alguien que yo controlo: MascotasRepositoryTestDouble
                                                                     Voy a montar un programa que cuando reciba la solicitud de guardar una mascota en BBDD devuelva siempre id=33!

                                                                        public class MascotasRepositoryTestDouble  implements MascotasRepository{

                                                                            public long save(Mascota){
                                                                                return 33;
                                                                            }

                                                                        }

                    // Mandar un email                             - Intervenir estas comunicaciones-->    Alguien que yo controlo: EmailsServiceTestDouble
                                                                     Voy a montar un programa que cuando reciba la solicitud de mandar un email haga NI PUTO CASO !
                                                                     pero... que deje registrada la solicitud
                                                                        public class EmailsServiceSpy implements EmailsService{

                                                                            private asuntoEnviado;          // Con su getter correspondientes!
                                                                            private destinatarioEnviado;    // Con su getter correspondientes!
                                                                            private cuerpoEnviado;          // Con su getter correspondientes!

                                                                            public void mandarCorreito(String asunto, String destinatario, String cuerpo){
                                                                                // Me parto y me mondo con tu petición!
                                                                                // Pero la registros qui en alguna variable
                                                                                this.asuntoEnviado= asunto;
                                                                                this.destinatarioEnviado= destinatario;
                                                                                this.cuerpoEnviado= cuerpo;
                                                                            }
                                                                        }
                }
            }

            Si hay un fallo en la prueba, dónde está el fallo ^^^^ ?
            - Ahora el fallo solo puede estar en el MascotasService

#### Tipo de prueba: unitaria

CASO DE PRUEBA1: Partiendo de datos guays,... que todo vaya como esperamos...
    Dado        los datos de una mascota (CORRECTOS)
                y un Servicio de mascotas integrado con un servicio de emails de mentirijilla (que no manda emails) y un repositorio de mentirijilla (que siempre devuelve 33)
    Cuando      llamo a altaDeMascota pasándole los datos de la mascota(esos guays... )
    Entonces    La función devuelve los datos de la mascota tras persistirlos o creerselo(que deben incluir un ID válido otorgado por el repositorio = 33)
              
CASI ! lo teníamos!
    El objetivo era ver que el código de Mascotas service era correcto.. por si mismo... lo he conseguido? 
    - He comprobado que estoy llamando al repositorio?
        Porque la función debería llamar al repositorio... me he asegurado? Más o menos si... si al final devuelve id 33... de donde coño ha salido eso? 
        O el tio que ha creado la función... ha metido "33" a fuego en el código.. o lo ha pedido a mi repo de mentirijilla.... Si no.. de dónde salió el 33? 
    - He comprobado que estoy llamando al servicio de emails?
        Porque la función debería llamar al servicio de emails... me he asegurado? NI DE LEJOS!!!!
        

CASO DE PRUEBA1: Partiendo de datos guays,... que todo vaya como esperamos...
    Dado        los datos de una mascota (CORRECTOS)
                y un Servicio de mascotas integrado con un servicio de emails de mentirijilla (que no manda emails) y un repositorio de mentirijilla (que siempre devuelve 33)
    Cuando      llamo a altaDeMascota pasándole los datos de la mascota(esos guays... )
    Entonces    La función devuelve los datos de la mascota tras persistirlos o creerselo(que deben incluir un ID válido otorgado por el repositorio = 33)
                Le pregunto al EmailsServiceSpy si: getAsuntoEnviado() == Al que debería haber enviado el servicio de mascotas, desde Alta de Mascota
                Le pregunto al EmailsServiceSpy si: getDestinatarioEnviado() == Al que debería haber enviado el servicio de mascotas, desde Alta de Mascota
                Le pregunto al EmailsServiceSpy si: getCuerpoEnviado() == Al que debería haber enviado el servicio de mascotas, desde Alta de Mascota

    AHORA SI !!!!.
        Me aseguro que el alta de animalitos, está llamando al repositorio (recibe 33)
        Y que ha llamado al servicio de emails con los datos esperados


CASO DE PRUEBA2: Partiendo de datos kk,... que el programa me devuelva una exception... lo que sea que haya definido (los requisitos)


### Caso 3:  Pruebo la función altaDeMascota, trabajando contra un Repositorio REAL y el Servicio de emails de Mentirijilla

            public class MascotasService{
                public DatosDeMascota altaDeMascota(DatosDeNuevaMascota datosDeNuevaMascota){
                    // Validación de los datos de entrada
                    // Persistencia                                Repositorio REAL

                    // Mandar un email                             - Intervenir estas comunicaciones-->    Alguien que yo controlo: EmailsServiceTestDouble
                                                                     Voy a montar un programa que cuando reciba la solicitud de mandar un email haga NI PUTO CASO !
                                                                     pero... que deje registrada la solicitud
                                                                        public class EmailsServiceSpy implements EmailsService{

                                                                            private asuntoEnviado;          // Con su getter correspondientes!
                                                                            private destinatarioEnviado;    // Con su getter correspondientes!
                                                                            private cuerpoEnviado;          // Con su getter correspondientes!

                                                                            public void mandarCorreito(String asunto, String destinatario, String cuerpo){
                                                                                // Me parto y me mondo con tu petición!
                                                                                // Pero la registros qui en alguna variable
                                                                                this.asuntoEnviado= asunto;
                                                                                this.destinatarioEnviado= destinatario;
                                                                                this.cuerpoEnviado= cuerpo;
                                                                            }
                                                                        }
                }
            }

Si la prueba falla... Y ya había hecho pruebas unitarias del MascotasService y del MascotasRepository... donde está el fallo? En la comunicación entre ellos

### Caso 4:  Pruebo la función altaDeMascota, trabajando contra un Repositorio de Mentirijilla y el Servicio de emails REAL

Si la prueba falla... Y ya había hecho pruebas unitarias del MascotasService y del EmailsService... donde está el fallo? En la comunicación entre ellos
... Quizás los datos no se mandan en formato adecuado... o el destinatario no existe
       vvv
        Dirección inválida de correo : http://animalitosFermin.com!


## En base al procedimiento de ejecución

- Dinámicas: Las que requieren echar el código a funcionar -> Detectan FALLOS
- Estáticas: Las que no                                    -> Detectan DEFECTOS
  - de Calidad de código
  - revisión de requisitos
  - revisión de un modelo de BBDD

## En base al conocimiento que tengo del sistema a probar

- Cajas blanca
- Caja negra

## Pruebas de Regresión

Cualquier prueba que VUELVO a ejecutar... para ver cuando toco algo que no haya jodido nada.
Casi todas las pruebas se me convierten en pruebas de regresión con las met. ágiles. -> AUTOMATIZARLAS

---

REQUISITO: El sistema debe tardar menos de 100ms en hacer una tarea concreta [RENDIMIENTO]
    ES UNA MIERDA !!!!!
REQUISITO: El sistema, instalado en un entorno con tales características, y estando sometido a tal carga de trabajo (que especifico)
    debe ofrecer un percentil 95 al realizar tal tarea (que detallo) inferior a 100ms
        -> PRUEBA: No funcionales - Rendimiento... a qué nivel hago esa prueba? Sistema (del sistema en su conjunto)

    Hago una prueba para medir la latencia de la red... (tengo que hacer queries a la BBDD...) Eso implica comunicación por red:
        -> PRUEBA: No funcionales - Rendimiento... a qué nivel hago esa prueba? Unitaria
            -> 50ms
        -> Si esto es así.... y el requisito de sistema es inamovible -> DECISIONES DE DISEÑO de mi sistema (CACHE!!!)

---

# Metodologías tradicionales > Metodologías waterfall... en cascada

El jefe de proyecto planificaba el proyecto de principio a fin... VAYA GILIPOLLEZ !!!!!
Diagrama de gantt

    TOMA DE REQUISITOS 
        ANALISIS / PLAN
            DESARROLLO (6 meses... 1 año... 2 años)
                PRUEBAS
                    PASE A PRODUCCION (Implantación+documentación....)

# Metodologías ágiles

De qué van? Entregar el proyecto de forma incremental. Esa es la característica principal...
            Objetivo: Tener un rápido FEEDBACK por parte del usuario/cliente !

Empiezo un proyecto:
- Dia 30 -> ENTREGA 1   5% de la funcionalidad (100% funcional)
    -> Hago paso a producción 
    -> Necesito hacer PRUEBAS... pero pruebas a nivel de producción!
        - 5% de la funcionalidad
- Dia 45 -> ENTREGA 2   +10% de la funcionalidad (100% funcional)
    -> Hago paso a producción 
    -> Necesito hacer PRUEBAS... pero pruebas a nivel de producción!
        - 5% + 10% de la funcionalidad
- Dia 60 -> ENTREGA 3   +15% de la funcionalidad (100% funcional)
    -> Hago paso a producción 
    -> Necesito hacer PRUEBAS... pero pruebas a nivel de producción!
        - 5% + 10% +15% de la funcionalidad
- Dia 75 -> ENTREGA 4   +10% de la funcionalidad (100% funcional)
    -> Hago paso a producción 
    -> Necesito hacer PRUEBAS... pero pruebas a nivel de producción!
        - 5% + 10% +15% + 10% de la funcionalidad

Hoy en día entendemos que un software está vivo... y por ende... no se cuándo ni cómo morirá!

Pero tienen otras características / objetivos:
- El método más eficiente y efectivo de comunicar información al equipo de desarrollo y entre sus miembros es la conversación cara a cara.
  -> SCRUM: Dailys 
- El software funcionando es la MEDIDA principal de progreso.
    La medida principal de progreso es el "software funcionando"
    La forma en la que mido qué tal vamos en el proyecto es el "software funcionando" -> INDICADOR ! METRICA 

    ¿Qué significa software funcionando? Software que funciona... 
        ¿Quién dice que funciona? Las pruebas!
            Cuantas pruebas nuevas hemos superado esta semana? 6/10
            Y la pasada: 5/10

    ¿Cómo mediamos antiguamente el grado de avance de un proyecto?
        HITOS: **Colección de requisitos** con una fecha objetivo
            Si llegada la fecha, no estaba implementada esa colección de requisitos -> Replanificar (cambiar la fecha)
                El proyecto va con retraso! 
            ¿Cómo sabíamos si el hito estaba cumplido? o era necesario replanificar? 
            - Preguntando a los desarrolladores. FELIPE, como var con los 5 requisitos que tenías que hacer? BIEN.. llevo 4
                -> Los desarrolladores de software MENTIMOS MAS QUE HABLAMOS ! Yo creía que si.. pero luego... resuelta que no... EN MI MAQUINA FUNCIONABA !
            - Contar el número de lineas de código escritas por semana! 

        SPRINTs: Colección de requisitos con una **fecha objetivo**
            Si llegada la fecha, no esta implementada esa colección de requisitos   -> Muevo los requisitos no implementados a la siguiente fecha de entrega

Las metodologías ágiles han traído a la mesa NUEVOS PROBLEMAS que ahora hay que resolver.
- ¿Cuántas veces hacía pruebas antes (con met. tradicionales)? 1 vez... cuando acababa el desarrollo.
Y ahora? En cada entrega (sprint)
- ¿Cuántas veces hacía pasos a producción (con met. tradicionales)? pocas... cuando acababa el desarrollo.
Y ahora? En cada entrega (sprint)

Con las met. ágiles :
- Las instalaciones se me disparan
- Y las pruebas se me super-disparan!

Y de dónde saco la pasta? para tanta instalación y pruebas? DE NINGUNA LAO !!!! NO LA HAY ni pasta
                   recursos                                                                  recursos
                   tiempo                                                                    tiempo

Y entonces?? 
Este problema solo tiene una solución: AUTOMATIZAR

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

            Automatizable?
PLAN
CODE
BUILD
TEST
RELEASE
DEPLOY
OPERATION
MONITOR

## Qué significa automatizar?

## Hacer un desarrollo ágil

## Integración Continua

## Entrega continua

## Despliegue continuo

En algún momento... nos saldrá que para adoptar una cultura devops, me hace falta un perfil nuevo que antes no necesitaba en las empresas... y que hay gente llamandole DEVOPS a ese perfil...
BIEN!... pero sabiendo a que llamo devops... como perfil... que cada empresa usa este término como le da la gana! MAL!!!!!

# JUNIT
# Mockito
# Cucumber
# Gherkin
# MAVEN
# Sonar
# Jenkins

## Cómo los principios SOLID de desarrollo de software os van a ayudar con estas cosas....
## Y cómo esos principios tiene su reflejo en el mundo del testing: FIRST


X Metodología que engloba herramientas de TODO EL ciclo de vida de un desarrollo ágil


---

# Definir una prueba:

Al definir una prueba siempre tengo en cuenta 3 cosas:
- Condiciones en la que ejecuto la prueba                   GIVEN   Dado
- La acción que quiero probar                               WHEN    Cuando 
- El resultado que espero                                   THEN    Entonces

---

# Test Doubles < Martin Fowler

Son objetos que usamos para hacer pruebas:
- Stub
- Fake
- Spy
- Mocks -> (Como sustituto de la palabra test-double)... pero esto es como llamar Cocacola a cualquier bebida de cola...
- Dummy

Mockito, una extraordinaria librería para generar TestDoubles... a todo le llama MOCK.... ya que hace gracia (buen marketing) con el nombre de la librería

Necesitamos testDoubles cuando queremos hacer pruebas (unitarias/Integración) que involucran a componentes con dependencias:

            Componente A                          -- en lo que mando   -->      ComponenteB
                                                  <- en lo que devuelvo --
             A a = ComponenteB.funcionA(b)                                      public A funcionA(B datos);

    - en lo que mando: SPY/MOCK
    - en lo que devuelvo: STUB/FAKE
    - En ocasiones pongo énfasis en todo!

Los test doubles son implementaciones parciales del ComponenteB
Qué me interesa implementar de ese componente B de cara a hacer una prueba en el componente A?
    - Si lo único que me interesa es tener unos datos de vuelta... controlados (id=33). Lo que necesito es "a".
        Montamos lo que se llama un STUB. En un Stb, me valen mierda los datos que me manden... Siempre mando lo mismo de vuelta.
        No valido los datos de entrada.

            ComponenteB: LoginService
```java
public interface DatosDeLogin{
    String getNombre();
    String getEmail();
}
public interface LoginService{
    DatosDeLogin doLogin(String usuario, String contraseña);
}
```
            Puede ser que el componenteA necesite unos datos de vuelta a, de tipo A para funcionar.
```java
public class LoginServiceStub interface LoginService{
    public DatosDeLogin doLogin(String usuario, String contraseña){
        return new DatosDeLoginImpl("Felipe","felipe@felipe.com");
    }
}
```

    - En ocasiones, vamos a utilizar mucho ese Stub... y lo quiero hacer un poco más listo... CREAMOS UN FAKE
        El fake es como el stub... devuelve una salida, pero condicionada a los datos de entrada... sin validarlos             
```java
public class LoginServiceFake interface LoginService{
    public DatosDeLogin doLogin(String usuario, String contraseña){
        if(usuario.equals(contraseña))
            return new DatosDeLoginImpl(usuario,usuario+"@"+usuario+".com");
        else
            throw new RuntimeException("Datos de usuario no reconocidos");
    }
}
```
        Si lo pensáis... a la que añado más y más complejidad al FAKE... en . qué se acaba convirtiendo? EN LA IMPLEMENTACION REAL !
    
    - La cosa... es que en ocasiones, lo que me interesa no son los datos de vuelta... quizás ni los hay... o no se usan para nada
        Tengo un componente A que hace:
            - llama a doLogin
            - llama a otra función de un componente B a la que le pasa los datos de doLogin: DatosDeLogin
      Lo que me puede interesar en otros escenarios en controlar los datos enviados... y que la llamada se realiza correctamente.
      Entonces monto un SPY. El SPY registra las llamadas... de forma que posteriormente pueda usar esos datos en un TEST para validar que la llamada se realizó correctamente
```java
public class LoginServiceSpy interface LoginService{
    private String usuario;     // Con sus getter()
    private String contraseña;  // Con sus getter()
    public DatosDeLogin doLogin(String usuario, String contraseña){
            this.usuario=usuario;
            this.contraseña = contraseña;
            return new DatosDeLoginImpl(usuario,usuario+"@"+usuario+".com");
    }
}
```
    - En ocasiones, lo que monto es un SPY, pero más listo... que lleve dentro la lógica para determinar si la llamada es buena o no..
      Al usar un mock, normalmente le digo de antemano los datos que va a recibir (.expectedData())... Y él comprueba que los datos que se reciban sean esos.
      Internamente mi código (el del componenteA) debería llamar a la función doLogin()...
      - Si llama con datos erroneos, distintos a los que he preconfigurado en el mock, falla
      - Pero si la función doLogin() no es invocada:
      Cuando acaba el test, le digo al mock que me diga si todo ha ido ok (validate())
```java
public class LoginServiceMock interface LoginService{
    private String receivedUsuario;     // Con sus getter()
    private String receivedContraseña;  // Con sus getter()
    private String expectedUsuario;     // Con sus getter()
    private String expectedContraseña;  // Con sus getter()
    public DatosDeLogin doLogin(String usuario, String contraseña){
        this.receivedUsuario=usuario;
        this.receivedContraseña = contraseña;
        validate();
        return new DatosDeLoginImpl(usuario,usuario+"@"+usuario+".com");
    }
    public void expectedData(String usuario, String contraseña){
        this.expectedUsuario=usuario;
        this.expectedContraseña = contraseña;
    }
    public void validate(){
        if(!receivedUsuario.equals(this.expectedUsuario) || !receivedContraseña.equals(this.expectedContraseña) )
            throw new Exception("Datos de mierda!!!!!");
    }
}
```
    El MOCK me da más flexibilidad que un SPY... igual que el FAKE me da mas flexibilidad que un STUB

En la práctica, muchas veces quiero montar objetos de prueba HIBRIDOS... MEzcla de un Stu/Spy... o de un FAKE/Spy... FAKE/Mock
Y no son objetos puros de ninguno de estos tipos

```java
public class LoginServiceMock/Fake interface LoginService{
    private String receivedUsuario;     // Con sus getter()
    private String receivedContraseña;  // Con sus getter()
    private String expectedUsuario;     // Con sus getter()
    private String expectedContraseña;  // Con sus getter()
    public DatosDeLogin doLogin(String usuario, String contraseña){
        this.receivedUsuario=usuario;
        this.receivedContraseña = contraseña;
        validate();
        if(usuario.equals(contraseña))
            return new DatosDeLoginImpl(usuario,usuario+"@"+usuario+".com");
        else
            throw new RuntimeException("Datos de usuario no reconocidos");
    }
    public void expectedData(String usuario, String contraseña){
        this.expectedUsuario=usuario;
        this.expectedContraseña = contraseña;
    }
    public void validate(){
        if(!receivedUsuario.equals(this.expectedUsuario) || !receivedContraseña.equals(this.expectedContraseña) )
            throw new Exception("Datos de mierda!!!!!");
    }
}
```

En general nos referimos a todos ellos como MOCKS....

Luego están los Dummy... esos van aparte!

Los Dummy los usamos cuando me vale mierda tanto la llamada como la devolución.
```java
public class LoginServiceDummy interface LoginService{
    public DatosDeLogin doLogin(String usuario, String contraseña){
        return null;
    }
}
```

Necesito algo al que pueda llamar... y me la trae el fresco lo que devuelva.
Por ejemplo... se os ocurre un caso en el que me interese un Dummy como éste concreto? 
Tengo un componenteA que va a solicitar login... y se la traen al fresco los datos de respuesta... quizás a otro le interesen... pero a éste NO!

    Si yo estoy haciendo una prueba que NO COMPRUEBA la comunicación entre ComponenteA y doLogin... me puede interesar un dummy: VELOCIDAD !

Necesito probar 80 características de un sistema, que requieren haber hecho login.
Ni quiero que explote... ni quiero que mire si los datos son correctos.. ni necesito comprobar los datos de vuelta... ni usarlos...SSolo necesito que se haya hecho login...
Un dummy... va rápido!

El dummy es la implementación más BASICA que puedo hacer de un componente:
    - Si tengo una función en un componente que devuelve un Object del tipo que sea: devuelvo null;
    - Si tengo una función que devuelve un número, devuelvo 0;
    - Si tengo  una función que devuelve un boolean: devuelvo false;
  