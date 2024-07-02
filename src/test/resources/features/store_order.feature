Feature: PetStore Order API

    Scenario Outline: Crear una nueva orden
        Given creo una nueva orden con id <orderId>, petId <petId> y cantidad <cantidad>
        When envío la solicitud para crear la orden
        Then debería recibir una respuesta con el código de estado 200
        And el cuerpo de la respuesta de creación debería contener el id de la orden <orderId>

        Examples:
            | orderId | petId | cantidad |
            | 1       | 1     | 1        |
            | 2       | 2     | 2        |

    Scenario Outline: Consultar una orden existente
        Given tengo una orden existente con id <orderId>
        When envío la solicitud para consultar la orden
        Then debería recibir una respuesta con el código de estado 200
        And el cuerpo de la respuesta debería contener el id de la orden <orderId>

        Examples:
            | orderId |
            | 1       |
            | 2       |
