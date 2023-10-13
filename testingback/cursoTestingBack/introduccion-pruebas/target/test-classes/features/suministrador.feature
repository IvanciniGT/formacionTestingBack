#language:es
# Este fichero le vamos a escribir usando un lenguaje llamado GHERKIN = pepinillo
# Cucumber = Pepino
# Cucumber es una librería que procesa archivos gherkin
# El lenguaje pepinillo, es un conjunto de restricciones que imponemos a un lenguaje natural (idioma de los que hablamos los seres humanos)

Característica: Suministrador de Diccionarios desde ficheros

  Escenario:    Preguntar por un diccionario que exista
    Dado        Que tenga un suministrador de diccionarios
     Y          que el suministrador de diccionario tiene un diccionario para idioma "ES"
    Cuando      pregunto al suministrador si tiene un diccionario del idioma "ES"
    Entonces    me responda que sí.

  Escenario:    Preguntar por un diccionario que no exista
    Dado        Que tenga un suministrador de diccionarios
    Y           que el suministrador de diccionario no tiene un diccionario para idioma "DE LOS ELFOS"
    Cuando      pregunto al suministrador si tiene un diccionario del idioma "DE LOS ELFOS"
    Entonces    me responda que no.

  Escenario:    Recuperar un diccionario que exista
    Dado        Que tenga un suministrador de diccionarios
    Y           que el suministrador de diccionario tiene un diccionario para idioma "ES"
    Cuando      solicito al suministrador un diccionario del idioma "ES"
    Entonces    me devuelve un diccionario
    Y           ese diccionario debe ser un diccionario de idioma "ES"

  Escenario:    Recuperar un diccionario que no exista
    Dado        Que tenga un suministrador de diccionarios
    Y           que el suministrador de diccionario no tiene un diccionario para idioma "DE LOS ELFOS"
    Cuando      solicito al suministrador un diccionario del idioma "DE LOS ELFOS"
    Entonces    no me devuelve ningún diccionario.

    # Lo que pruebo aquí es el API: SISTEMA
  # Si yo tengo en cuenta que mi implementación va a connectarse con una BBDD
  # Y hago una prueba aislando a mi implementación de la BBDD ... será unitaria