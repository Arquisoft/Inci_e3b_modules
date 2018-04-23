# language: es
Característica: Disponibilidad de los servicios
  
  Como usuario, 
  Quiero tener disponible todos los servicios implicados
  Y de ese modo poder procesar incidencias.

  Esquema del escenario: Disponibilidad del servicio: "<servicio>"
    Cuando el usuario quiere disponer del servicio: "<servicio>"
    Entonces el usuario tiene disponible la página web del servicio: "<servicio>" en la dirección: "<direccion>"

    Ejemplos: 
      | servicio      | direccion      |
      | Agents        | localhost:8090 |
      | InciManager   | localhost:8091 |
      | InciDashboard | localhost:8092 |
