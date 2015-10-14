# language: es
Característica: Compra una torta
  Efectua la carga de un carrito.

  Esquema del escenario:
  Carga el carrito con una torta, y valida el precio,
  con un descuento de primera compra.
    Dado soy un usuario nuevo
    Y que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Cuando ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar '<precio>' en el carrito con descuento '<descuento>'

    Ejemplos:
      | kilo | per_kilo |   precio | descuento |
      |  1   |   30     |    30    | 0.15      |
      |  2   |   30     |    60    | 0.15      |
      |  3   |   30     |    90    | 0.15      |