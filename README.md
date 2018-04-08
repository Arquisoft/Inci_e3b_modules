# Inci_e3b_submodules
Integración los 4 módulos de la práctica de Arquitectura del Software mediante git submodules. 

Estado de la **integración continua** de los proyectos:

## [InciManager_e3b »](https://github.com/Arquisoft/InciManager_e3b/) 

[![Build Status](https://travis-ci.org/Arquisoft/InciManager_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciManager_e3b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6b9e962e78224811933f6fc1025a2b3f)](https://www.codacy.com/app/TonyMarin/InciManager_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciManager_e3b&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/InciManager_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciManager_e3b)
[![Gitter](https://badges.gitter.im/Arquisoft/InciManager_e3b.svg)](https://gitter.im/Arquisoft/InciManager_e3b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## [Agents e3b »](https://github.com/Arquisoft/Agents_e3b/)

[![Build Status](https://travis-ci.org/Arquisoft/Agents_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/Agents_e3b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/52c0a7fa26854206a17e11d781bd421c)](https://www.codacy.com/app/jelabra/Agents_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Agents_e3b&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/Agents_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Agents_e3b)
[![Gitter](https://badges.gitter.im/Arquisoft/Agents_e3b.svg)](https://gitter.im/Arquisoft/Agents_e3b?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## [Loader e3b »](https://github.com/Arquisoft/Loader_e3b/)

[![Build Status](https://travis-ci.org/Arquisoft/Loader_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/Loader_e3b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6fad6fe134c1434cb0b9384d851821c8)](https://www.codacy.com/app/jelabra/Loader_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/Loader_e3b&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/Loader_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/Loader_e3b)

## [InciDashBoard_e3b »](https://github.com/Arquisoft/InciDashboard_e3b/)

[![Build Status](https://travis-ci.org/Arquisoft/InciDashboard_e3b.svg?branch=master)](https://travis-ci.org/Arquisoft/InciDashboard_e3b)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/20f4862789f44608a8d6781dcacfda57)](https://www.codacy.com/app/UO252010/InciDashboard_e3b?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Arquisoft/InciDashboard_e3b&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/Arquisoft/InciDashboard_e3b/branch/master/graph/badge.svg)](https://codecov.io/gh/Arquisoft/InciDashboard_e3b)
[![Gitter](https://badges.gitter.im/Arquisoft/InciDashboard_e3b.svg)](https://gitter.im/inciDashboard_e3b/Lobby?utm_source=share-link&utm_medium=link&utm_campaign=share-link)

# Actualizar módulos
Los 4 repositorios incluidos como submódulos, hacen referencia a un commit del repositorio original ». Para actualizar la referencia de cada módulo a la última versión, hay que ejectuar desde el directorio raiz del repositorio el siguiente comando:
```
git submodule foreach git pull origin master
```

También se puede ejecutar el archivo **update_modules.bat**, que contiene el comando anterior.

# Ejecución de todos los módulos
1. tiene que estar ejecutándose la base de datos hsqldb, para arrancarla hay que ejecutar el fichero `hsqldb\bin\runServer.bat`

2. Para arrancar todos los servicios hay que ejecutar el fichero batch `execute_modules.bat`. 

Este fichero ejecutará Apache Zookeeper, Apache Kafka y después los módulos de Agentes, InciManager e InciDashboard, para su poder testear la funcionalidad.