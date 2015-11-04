# language: es
Característica: Prueba para analizar distintos tipos de descuentos.
  Efectua compras con un usuario y valida que se apliquen los descuentos correspondientes.

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
    Y ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar '<precio>' en el carrito con descuento '<descuento>'

  Ejemplos:
    | kilo | per_kilo |   precio | descuento | cobertura      | cobertura_precio |
    |  1   |   30     |    90    | 0.15      | test_chispas   |         15       |
    |  2   |   30     |    150   | 0.15      | test_chispas   |         15       |
    |  3   |   30     |    240   | 0.15      | test_chocolate |         30       |


  @comercio @descuento
  Esquema del escenario:
  Carga el carrito con una torta, con cobertura y valida el precio,
  con un descuento de primera compra, y descuento por cantidad
    Dado soy un usuario nuevo
    Y que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y una cobertura de '<cobertura>' con precio '<cobertura_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Y ordeno una torta de '<kilo>' kilos
    Y la agrego al carrito
    Entonces debo pagar '<precio>' en el carrito con descuento '<descuento>' y '<desc_cant>' por cantidad

  Ejemplos:
    | kilo | per_kilo |   precio | descuento | cobertura      | cobertura_precio | desc_cant |
    |  4   |   30     |    270   | 0.15      | test_chispas   |         15       |    0.1    |
    |  5   |   30     |    360   | 0.15      | test_chocolate |         30       |    0.1    |


