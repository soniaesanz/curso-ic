Feature: Se puede dar de alta un cliente
	Como gerente de ventas
	Quiero que se puedan dar de alta clientes
	Para poder contactarlos fácilmente

  Scenario: Un cliente inexistente es creado
    Given no existe el cliente 'Pedro'
    When creo el cliente 'Pedro'
    Then el listado de clientes incluye a 'Pedro'