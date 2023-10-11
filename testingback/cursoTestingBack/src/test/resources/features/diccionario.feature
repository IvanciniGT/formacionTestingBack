#language:es

Característica: Diccionarios desde ficheros

  Esquema del escenario: Preguntar por una palabra que existe
    Dado     Que tenga un suministrador de diccionarios
       Y     que tengo un diccionario de idioma "ES"
    Cuando   pregunto por la palabra "<palabra>"
    Entonces me responde que si existe

    Ejemplos:
    | palabra   |
    | manzana   |
    | melocotón |

  Escenario: Preguntar por una palabra que no existe
    Dado     Que tenga un suministrador de diccionarios
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   pregunto por la palabra "archilococo"
    Entonces me responde que no existe

  Esquema del escenario: Recuperar los significados de una palabra que existe
    Dado     Que tenga un suministrador de diccionarios
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "<palabra>"
    Entonces me devuelve 1 significado
    Y        el significado número 1 es "<significado>"

    Ejemplos:
      | palabra   |   significado           |
      | manzana   | Fruto del manzano      |
      | melocotón | Fruto del melocotonero |

  Escenario: Recuperar los significados de una palabra que existe
    Dado     Que tenga un suministrador de diccionarios
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "melón"
    Entonces me devuelve 2 significados
    Y        el significado número 1 es "Fruto del melonero"
    Y        el significado número 2 es "Persona con pocas luces"

  Escenario: Recuperar los significados de una palabra que no existe
    Dado     Que tenga un suministrador de diccionarios
    Dado     que tengo un diccionario de idioma "ES"
    Cuando   solicito los significados de la palabra "archilococo"
    Entonces no me devuelve nada

