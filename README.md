# Inci_e3b_submodules
Integración los 4 módulos de la práctica de Arquitectura del Software mediante git submodules. 

Estado de la **integración continua** de los proyectos:

| Módulo | | | | | |
|---------------------|---|---|---|---|---|
| [InciManager_e3b »](https://github.com/Arquisoft/InciManager_e3b/) | [![Build Status](https://travis-ci.org/Arquisoft/InciManager_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e3b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/6b9e962e78224811933f6fc1025a2b3f)](https://www.codacy.com/app/TonyMarin/InciManager_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e3b&amp;utm_campaign=Badge_Grade)|[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e3b) | [![Gitter](https://badges.gitter.im/Arquisoft/InciManager_e3b.svg)](https://gitter.im/Arquisoft/InciManager_e3b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge) | [![GitHub issues](https://img.shields.io/github/issues/Arquisoft/InciManager_e3b.svg)](https://github.com/Arquisoft/InciManager_e3b/issues) 
| [Agents e3b »](https://github.com/Arquisoft/Agents_e3b/) | [![Build Status](https://travis-ci.org/Arquisoft/Agents_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_e3b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/52c0a7fa26854206a17e11d781bd421c)](https://www.codacy.com/app/jelabra/Agents_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_e3b&amp;utm_campaign=Badge_Grade)|[![codecov](https://codecov.io/gh/Arquisoft/Agents_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_e3b) | [![Gitter](https://badges.gitter.im/Arquisoft/Agents_e3b.svg)](https://gitter.im/Arquisoft/Agents_e3b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge) | [![GitHub issues](https://img.shields.io/github/issues/Arquisoft/Agents_e3b.svg)](https://github.com/Arquisoft/Agents_e3b/issues)
| [Loader e3b »](https://github.com/Arquisoft/Loader_e3b/) | [![Build Status](https://travis-ci.org/Arquisoft/Loader_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/Loader_e3b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/6fad6fe134c1434cb0b9384d851821c8)](https://www.codacy.com/app/jelabra/Loader_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Loader_e3b&amp;utm_campaign=Badge_Grade)|[![codecov](https://codecov.io/gh/Arquisoft/Loader_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Loader_e3b) | | [![GitHub issues](https://img.shields.io/github/issues/Arquisoft/Loader_e3b.svg)](https://github.com/Arquisoft/Loader_e3b/issues)
| [InciDashBoard_e3b »](https://github.com/Arquisoft/InciDashboard_e3b/) | [![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e3b) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/20f4862789f44608a8d6781dcacfda57)](https://www.codacy.com/app/UO252010/InciDashboard_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciDashboard_e3b&amp;utm_campaign=Badge_Grade) | [![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e3b) | [![Gitter](https://badges.gitter.im/Arquisoft/InciDashboard_e3b.svg)](https://gitter.im/inciDashboard_e3b/Lobby?utm_source=share-link&utm_medium=link&utm_campaign=share-link) | [![GitHub issues](https://img.shields.io/github/issues/Arquisoft/InciDashboard_e3b.svg)](https://github.com/Arquisoft/InciDashboard_e3b/issues)

## Clonar repositorio
Para clonar el repositorio con los submódulos incluidos, debe hacerse con el siguiente comando:
```
git clone https://github.com/Arquisoft/Inci_e3b_modules.git --recurse-submodules
```
## Actualizar módulos
Los 4 repositorios incluidos como submódulos, hacen referencia a un commit del repositorio original. Para actualizar la referencia de cada módulo a la última versión, hay que ejectuar desde el directorio raiz del repositorio el siguiente comando:
```
git submodule foreach git pull origin master
```

También se puede ejecutar el archivo **update_modules.bat**, que contiene el comando anterior.

# Ejecución de todos los módulos
## Despliegue manual
* Para arrancar todos los servicios (incluida la base de datos) hay que ejecutar el fichero batch **execute_modules.bat**. 

Este fichero batch lleva a cabo las siguientes acciones:
1. Elimina los ficheros temporales de kafka (topics, logs etc...) para evitar colisiones y logs corruptos durante la ejecución
2. Ejecuta una instancia la base de datos HSQLDB
3. Inicia Apache Zookeeper y Apache Kafka 
4. Finalmente ejecuta los módulos de Agentes, InciManager e InciDashboard, para así poder testear la funcionalidad completa.

## Despliegue automático mediante contenedores Docker

Si se dispone de una instancia de [Docker](https://www.docker.com) ya instalada,
 es posible desplegar automáticamente todos los servicios necesarios utilizando 
 la herramienta de orquestación de contenedores 
 [Docker-Compose](https://docs.docker.com/compose/overview). Para ello, basta 
 con situarse en el directorio raiz del proyecto (donde se encuentra el fichero
  [docker-compose.yml](docker-compose.yml)) y ejecutar en la consola:

~~~batchfile
docker-compose up
~~~

De forma opcional, es posible combinar al despliegue anterior diversos contenedores 
auxiliares destinados a gestionar y supervisar el funcionamiento de dichos servicios: 

~~~batchfile
docker-compose -f docker-compose.yml -f docker-compose.management.yml up
~~~
 
 Una vez desplegado, cada contenedor es accesible a través del puerto 
 correspondiente del servidor Docker donde se ejecutan dichos contenedores (en 
 el caso de tratarse de la misma máquina, puede sustituirse por la combinación:
 *localhost:puerto*):
  
 |  CONTENEDOR   | DIRECCIÓN                                   | FICHERO                                                        |
 |---------------|---------------------------------------------|----------------------------------------------------------------|
 | postgres      | [postgres:5432](http://localhost:5432)      | [docker-compose.yml](docker-compose.yml)                       |
 | zookeeper     | [zookeeper:2181](http://localhost:2181)     | [docker-compose.yml](docker-compose.yml)                       |
 | kafka         | [kafka:9092](http://localhost:9092)         | [docker-compose.yml](docker-compose.yml)                       |
 | pgadmin4      | [pgadmin4:5433](http://localhost:5433)      | [docker-compose.management.yml](docker-compose.management.yml) |
 | kafka-manager | [kafka-manager:9000](http://localhost:9000) | [docker-compose.management.yml](docker-compose.management.yml) |
 
**IMPORTANTE:** En el caso de utilizar *Docker Compose* con *Docker Toolbox/Machine*
 en *MS-Windows*,  es necesario establecer primero la variable de entorno 
 [COMPOSE_CONVERT_WINDOWS_PATHS=1](https://docs.docker.com/compose/reference/envvars/#compose_convert_windows_paths)
  antes de poder ejecutar con exito `docker-compose` 
  (*[breaking changes 1.9.0 (2016-11-16)](https://github.com/docker/compose/blob/master/CHANGELOG.md#190-2016-11-16))*. 

Para detener la ejecución de todos los servicios de forma interactiva es 
suficiente con la combinación de teclas Ctrl+C o en su defecto, ejecutando la orden:

~~~batchfile
docker-compose down
~~~

Para eliminar en MS-Windows todos los objetos creados en Docker y reiniciar el 
despliegue desde cero puede ejecutarse la siguiente secuencia de ordenes 
(disponibles en el fichero por lotes: 
'[bin/docker-remove-objects.bat](bin/docker-remove-objects.bat)') :

~~~batchfile
@echo off
REM Stop all containers: 
docker ps
FOR /f "tokens=*" %%i IN ('docker ps -aq') DO docker container stop %%i 
REM Remove all container:
docker container ls
FOR /f "tokens=*" %%i IN ('docker ps -aq') DO docker container rm %%i 
REM Remove all volumes:
docker volume ls
FOR /f "tokens=*" %%i IN ('docker volume ls -q') DO docker volume rm %%i 
REM Remove all networks:
docker network ls
FOR /f "tokens=*" %%i IN ('docker network ls -q') DO docker network rm %%i 
REM Remove all images:
docker image ls
FOR /f "tokens=*" %%i IN ('docker images --format "{{.ID}}"') DO docker rmi %%i
pause
~~~

## Como probar el proyecto

### Ejecución de las pruebas de aceptación de usuario (User Acceptance Tests)

Para ejecutar exclusivamente el conjunto de pruebas de aceptación dentro de la
fase `verify` de Maven (e independientemente del resto de pruebas de 
integración), puede hacerse ejecutando:

~~~batchfile
mvn -Dit.test=AcceptanceTests verify 
~~~

Si además se quiere limitar la ejecución a una caracteristica (*feature*) concreta 
del conjunto de caracteristicas disponibles en el directorio `src/test/resources/features/`:

~~~batchfile
mvn -Dit.test=AcceptanceTests \
    -Dcucumber.options="src/test/resources/features/autenticacion.feature"\
    verify
~~~

Opcionalmente tambien es posible limitar la ejecución al conjunto de escenarios 
etiquetados por una o varias etiquetas:

~~~batchfile
mvn -Dit.test=AcceptanceTests -Dcucumber.options="--tags @qa_ready,@login" verify
~~~

Concluida la ejecución de las pruebas es posible consultar el resultado de las 
mismas en el fichero de salida: `${project.build.directory}/cucumber/index.html`

