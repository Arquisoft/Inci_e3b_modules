# language: es
Característica: Disponibilidad de los servicios
  
  Como usuario, 
  Quiero tener disponible todos los servicios implicados
  Y de ese modo poder procesar incidencias.

  Esquema del escenario: Disponibilidad del servicio: "<service>"
    Dado el servicio: "<service>" desplegado en el puerto: "<port>" del anfitrión: "<host>"
    Cuando el usuario quiere disponer del servicio: "<service>"
    Entonces se encuentra disponible la dirección web: "http://<host>:<port>/"

    Ejemplos: 
      | service       | host      | port |
      | Agents        | localhost | 8090 |
      | InciManager   | localhost | 8091 |
      | InciDashboard | localhost | 8092 |
