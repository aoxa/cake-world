# language: es
Característica: Compra una torta
  Efectua la carga de un carrito.

  Esquema del escenario: Carga el carrito con una torta.
    Dado soy un usuario nuevo
    Y que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Cuando ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar en el carrito '<precio>'

    Ejemplos:
      | kilo | per_kilo |   precio |
      |  1   |   30     |    30    |
      |  2   |   30     |    60    |
      |  3   |   30     |    90    |