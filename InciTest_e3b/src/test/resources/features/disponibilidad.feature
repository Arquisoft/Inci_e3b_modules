# language: es
Característica: Disponibilidad de los servicios
  
  Como usuario, 
  Quiero tener disponible todos los servicios implicados
  Y de ese modo poder procesar incidencias.

  Esquema del escenario: Disponibilidad del servicio: "<servicio>"
    Dado el servicio: "<servicio>" desplegado en el puerto: "<puerto>" del anfitrión: "<anfitrión>"
    Cuando el usuario quiere disponer del servicio: "<servicio>"
    Entonces se encuentra disponible la dirección web: "http://<anfitrión>:<puerto>/"

    Ejemplos: 
      | servicio      | anfitrión | puerto |
      | Agents        | localhost |   8090 |
      | InciManager   | localhost |   8091 |
      | InciDashboard | localhost |   8092 |
