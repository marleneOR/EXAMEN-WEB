Feature: Product - Store
  Como cliente de la tienda Store
  Quiero iniciar sesión y realizar la compra de un producto
  Para validar que el proceso de compra y los cálculos de precios se realicen correctamente
  @testStore
  Scenario Outline: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logueo con mi usuario "<user>" y clave "<password>"
    When navego a la categoria "<category>" y subcategoria "<subcategory>"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito
    Examples:

      |user                              |password       | category    | subcategory |
      | orihuelmarlene271@gmail.com      |P@@sswword100  |  Clothes    |     Men     |
      | orihuelmarlene271@gmail.com      |P@@sswword122  |  Clothes    |     Men     |
      | orihuelmarlene271@gmail.com      |P@@sswword100  |  Shoes      |     Men     |