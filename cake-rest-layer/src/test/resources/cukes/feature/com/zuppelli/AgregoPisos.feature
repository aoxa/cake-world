# language: es
Característica: Agrega una torta
  Efectua cambios en la cantidad de pisos que la misma tiene.

    Escenario: Prueba crear una torta sin pisos.
      Dado que agrego una nueva torta
      Cuando ordeno una torta de '3' kilos
      Entonces recuperarla y validar el contenido

    Escenario: Prueba crear una torta con un piso.
      Dado que agrego una nueva torta
      Cuando ordeno una torta de '3' kilos
      Y agrego un piso
      Entonces recuperarla y validar el contenido