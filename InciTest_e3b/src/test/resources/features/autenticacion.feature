# language: es
Característica: Autenticación de agentes
  
  Como agente registrado en el sistema, 
  quiero acceder a cualquiera de los servicios necesarios 
  para poder procesar incidencias.

	@qa_ready @autenticacion @rest @agents
  Esquema del escenario: Consulta de información sobre un agente utilizando REST API
    Dado un agente previamente registrado en el sistema con el nombre de usuario: "<username>" y la contraseña: "<password>"
    Y con el tipo de agente: "<kind>"
    Cuando invoco el servicio con la siguiente solicitud en formato JSON:
      """
      {"login": "<username>", "password": "<password>", "kind": "<kind>"}
      """
    Entonces el servicio devuelve la siguiente respuesta en formato JSON:
      """
      { 
        "nombre": "<name>", 
        "location": "<location>", 
        "email": "<email>", 
        "id": "<username>", 
        "kind": "<kind>",
        "kindCode": <kindCode>
      }
      """

    Ejemplos: 
      | name                     | location               | email                   | username                | password | kind   | kindCode |
      | Paco González            |                        | paco@gmail.com          | paco@gmail.com          |   123456 | Person |        1 |
      | Pepe Fernandez           |                        | pepe@gmail.com          | pepe@gmail.com          |   123456 | Person |        1 |
      | Sensor_123 2018          | 43.361368, -5.853591   | admin@sensores.com      | admin@sensores.com      |   123456 | Sensor |        3 |
      | Ministerio Medioambiente | 43.359486, -5.846986   | ambiente@ministerio.com | ambiente@ministerio.com |   123456 | Entity |        2 |
      | Space X sensor model A   | 33.921209, -118.327940 | musk@spacex.com         | musk@spacex.com         |   123456 | Sensor |        3 |

	@qa_ready @autenticacion @login @agents @incimanager @dashboard
  Esquema del escenario: Autenticación Web de agentes registrados
    Dado un agente previamente registrado en el sistema con el nombre de usuario: "<username>" y la contraseña: "<password>"
    Y no puedo acceder a la página principal del servicio: "<service>"
    Cuando visito la página de inicio de sesión del servicio: "<service>"
    Y introduzco el nombre de usuario: "<username>" en el formulario
    Y introduzco la contraseña de usuario: "<password>" en el formulario
    Y presiono el botón de confirmación
    Entonces puedo acceder a la página principal del servicio: "<service>"

    Ejemplos: 
      | service       | username                | password |
      | InciManager   | paco@gmail.com          |   123456 |
      | InciManager   | pepe@gmail.com          |   123456 |
      | InciManager   | admin@sensores.com      |   123456 |
      | InciManager   | ambiente@ministerio.com |   123456 |
      | InciManager   | musk@spacex.com         |   123456 |
	  | InciDashboard | oper12@gmail.es         |   123456 |

	@qa_ready @autenticacion @login @agents @incimanager @dashboard
  Esquema del escenario: Fallo de autenticación Web de agentes no registrados
    Dado ningún agente registrado en el sistema con el nombre de usuario: "<username>" y la contraseña: "<password>"
    Y no puedo acceder a la página principal del servicio: "<service>"
    Cuando visito la página de inicio de sesión del servicio: "<service>"
    Y introduzco el nombre de usuario: "<username>" en el formulario
    Y introduzco la contraseña de usuario: "<password>" en el formulario
    Y presiono el botón de confirmación
    Entonces recibo una notificación de error de acceso al servicio: "<service>"
    Y no puedo acceder a la página principal del servicio: "<service>"

    Ejemplos: 
      | service       | username           | password         |
      | InciManager   | paco@gmail.com     | INVALID_PASSWORD |
      | InciManager   | unregistered@agent | ANY_PASSWORD     |
	  | InciDashboard | opUnreg@gmail.com  | ANY_PASSWORD     |
