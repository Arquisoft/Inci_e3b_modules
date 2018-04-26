# language: es
Característica: Autenticación de agentes
  
  Como agente registrado en el sistema, 
  quiero acceder al servicio 
  para poder procesar incidencias.

  Esquema del escenario: Autenticación interactiva de agentes previamente registrados
    Dado un agente previamente registrado en el sistema con el nombre de usuario "<username>" y la contraseña "<password>"
    Y no puedo acceder a la página principal del servicio: "<service>"
    Cuando visito la página de inicio de sesión del servicio: "<service>"
    Y introduzco el nombre de usuario: "<username>" en el formulario
    Y introduzco la contraseña de usuario: "<password>" en el formulario
    Y presiono el botón de confirmación
    Entonces puedo acceder a la página principal del servicio: "<service>"

    Ejemplos: 
      | service     | username                | password | kind   |
      | InciManager | paco@gmail.com          |   123456 | Person |
      | InciManager | pepe@gmail.com          |   123456 | Person |
      | InciManager | admin@sensores.com      |   123456 | Sensor |
      | InciManager | ambiente@ministerio.com |   123456 | Entity |
      | InciManager | musk@spacex.com         |   123456 | Sensor |

  Esquema del escenario: Fallo de autenticación interactiva de agentes no registrados
    Dado ningún agente registrado en el sistema con el nombre de usuario "<username>" y la contraseña "<password>"
    Y no puedo acceder a la página principal del servicio: "<service>"
    Cuando visito la página de inicio de sesión del servicio: "<service>"
    Y introduzco el nombre de usuario: "<username>" en el formulario
    Y introduzco la contraseña de usuario: "<password>" en el formulario
    Y presiono el botón de confirmación
    Entonces recibo una notificación de error de acceso al servicio: "<service>"
    Y no puedo acceder a la página principal del servicio: "<service>"

    Ejemplos: 
      | service     | username           | password         | kind   |
      | InciManager | paco@gmail.com     | INVALID_PASSWORD | Person |
      | InciManager | unregistered@agent | ANY_PASSWORD     | Person |
