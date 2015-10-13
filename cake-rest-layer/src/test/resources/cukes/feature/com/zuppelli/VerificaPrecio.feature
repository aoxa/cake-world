# language: es
Característica: Verifica Precio
  Verifica los precios de una torta de un solo piso.

  Esquema del escenario: Prueba de precio torta por kilo.
    Dado que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Cuando ordeno una torta de '<kilo>' kilos
    Entonces debo pagar '<precio>'

    Ejemplos:
      | kilo | per_kilo |   precio |
      |  1   |   30     |    30    |
      |  2   |   30     |    60    |
      |  3   |   30     |    90   |

  Esquema del escenario: Prueba de precio por kilo y con cobertura.
    Dado que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y una cobertura de '<cobertura>' con precio '<cobertura_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Entonces debo pagar '<precio>'

    Ejemplos:
      |    cobertura      | cobertura_precio | kilo | per_kilo |   precio |
      | test_chispas      |         15       |  1   |   30     |    45    |
      | test_chispas      |         15       |  2   |   30     |    75    |
      | test_pasta        |         25       |  2   |   30     |    85   |

  Esquema del escenario: Prueba de precio por kilo y con relleno.
    Dado que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y un relleno de '<relleno>' con precio '<relleno_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Entonces debo pagar '<precio>'

    Ejemplos:
      |       relleno     | relleno_precio   | kilo | per_kilo |   precio |
      | test_chocolate    |        20        |  1   |   30     |    50    |
      | test_chocolate    |        20        |  2   |   30     |    80    |
      | test_merengues    |        15        |  2   |   30     |    75   |


  Esquema del escenario: Prueba de precio por kilo, con relleno y cobertura.
    Dado que quiero comprar una torta usando
    Y un costo por kilo de '<per_kilo>'
    Y una cobertura de '<cobertura>' con precio '<cobertura_precio>'
    Y un relleno de '<relleno>' con precio '<relleno_precio>'
    Cuando ordeno una torta de '<kilo>' kilos
    Entonces debo pagar '<precio>'

  Ejemplos:
    |    cobertura      | cobertura_precio |       relleno     | relleno_precio   | kilo | per_kilo |   precio |
    | test_chispas      |         15       | test_chocolate    |        20        |  1   |   30     |    65    |
    | test_chispas      |         15       | test_chocolate    |        20        |  2   |   30     |    95    |
    | test_pasta        |         25       | test_merengues    |        15        |  2   |   30     |    100   |