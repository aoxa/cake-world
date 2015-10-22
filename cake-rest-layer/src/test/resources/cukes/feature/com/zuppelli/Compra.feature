# language: es
Característica: Compra una torta
  Efectua la carga de un carrito.

  @comercio @descuento
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

  @comercio @descuento
  Esquema del escenario:
  Carga el carrito con una torta, con cobertura y valida el precio,
  con un descuento de primera compra.
    Dado soy un usuario nuevo
    Y que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y una cobertura de '<cobertura>' con precio '<cobertura_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar '<precio>' en el carrito con descuento '<descuento>'

  Ejemplos:
    | kilo | per_kilo |   precio | descuento | cobertura      | cobertura_precio |
    |  1   |   30     |    45    | 0.15      | test_chispas   |         15       |
    |  2   |   30     |    75    | 0.15      | test_chispas   |         15       |
    |  3   |   30     |    120   | 0.15      | test_chocolate |         30       |

  @comercio
  Esquema del escenario:
  Carga el carrito con una torta, con cobertura y valida el precio.
    Dado soy un usuario que ya hizo una compra
    Y que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y una cobertura de '<cobertura>' con precio '<cobertura_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar '<precio>' en el carrito

  Ejemplos:
    | kilo | per_kilo |   precio | cobertura      | cobertura_precio |
    |  1   |   30     |    45    | test_chispas   |         15       |
    |  2   |   30     |    75    | test_chispas   |         15       |
    |  3   |   30     |    120   | test_chocolate |         30       |