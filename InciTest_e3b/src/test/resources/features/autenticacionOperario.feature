# language: es
Característica: Autenticación de operarios
  
  Como operario registrado en el sistema, 
  quiero acceder a cualquiera de los servicios necesarios 
  para poder operar incidencias.

   @autenticacion  @dashboard
  Esquema del escenario: Autenticación Web de un operario del "<service>"
    Dado un operario registrado en el sistema con el email: "<email>" y la contraseña: "<password>"
    Cuando invoco el servicio de inicio de sesion  del servicio "<service>"
	Y introduzco el email "<email>" en el formulario
	Y introduzco la contraseña "<password>" en el formulario 
	Y presiono el boton de login
    Entonces puedo acceder al sistema como operario.
	
    Ejemplos: 
     |service        |   email                    |  password |
	 |InciDashboard  | oper12@gmail.es            |   123456  |
	  
	
	@qa_ready @autenticacion @login  @dashboard
  Esquema del escenario: Fallo de autenticación Web de operarios no registrados
    Dado ningún operario registrado en el sistema con el email : "<email>" y la contraseña: "<password>"
    Y no puedo acceder a la página principal del servicio: "<service>"
    Cuando visito la página de inicio de sesión del servicio: "<service>"
    Y introduzco el email : "<email>" en el formulario
    Y introduzco la contraseña  "<password>" en el formulario
    Y presiono el botón de confirmación
    Entonces no puedo acceder a la página principal del servicio: "<service>"

    Ejemplos: 
      | service     | username           | password         |
      | InciDashboard | oper@gmail.com     | INVALID_PASSWORD |
      