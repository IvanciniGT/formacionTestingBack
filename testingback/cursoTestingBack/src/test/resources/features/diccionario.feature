#language:es

Característica: Diccionarios desde ficheros

  Escenario: Preguntar por una palabra que existe
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   pregunto por la palabra "manzana"
    Entonces me responde que si existe

  Escenario: Preguntar por una palabra que no existe
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   pregunto por la palabra "archilococo"
    Entonces me responde que no existe

  Escenario: Recuperar los significados de una palabra que existe
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "manzana"
    Entonces me devuelve 1 significado
    Y        el significado número 1 es "Fruto del manzano"

  Escenario: Recuperar los significados de una palabra que existe
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "melón"
    Entonces me devuelve 2 significados
    Y        el significado número 1 es "Fruto del melonero"
    Y        el significado número 2 es "Persona con pocas luces"

  Escenario: Recuperar los significados de una palabra que no existe
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "archilococo"
    Entonces no me devuelve nada

