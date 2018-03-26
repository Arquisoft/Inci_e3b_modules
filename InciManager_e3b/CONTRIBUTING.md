# Como contribuir al proyecto

## Tabla de Contenidos

* [Código de Conducta](#c-digo-de-conducta)
* [Gestión colaborativa del proyecto](#gesti-n-colaborativa-del-proyecto)
* [Flujo de trabajo para los contribuyentes](#flujo-de-trabajo-para-los-contribuyentes)
* [Formato mensajes de confirmación](#formato-mensajes-de-confirmaci-n)

## Código de conducta

El *"[Código de Conducta Convenido Para Contribuyentes](CODE_OF_CONDUCT.md)"* es una adaptación del *"[Contributor Covenant](http://wwww.contributor-covenant.org)"*, versión 1.4, su contenido completo puede consultarse en el fichero: [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md).


## Gestión colaborativa del proyecto

- El repositorio oficial del proyecto se encuentra alojado en GitHub en la dirección [https://github.com/Arquisoft/InciManager_e3b](https://github.com/Arquisoft/InciManager_e3b).
- Los informes de fallos y asuntos pendientes son gestionados a traves del apartado *[Issues](https://github.com/Arquisoft/InciManager_e3b/issues)* del repositorio oficial. 
- Las discusiones y novedades sobre el proyecto tienen lugar a través del [chat oficial del proyecto en Gitter](https://gitter.im/Arquisoft/InciManager_e3b).

## Flujo de trabajo para los contribuyentes

- [Asignarse](https://help.github.com/articles/assigning-issues-and-pull-requests-to-other-github-users) alguno de los *[issues](https://github.com/Arquisoft/InciManager_e3b/issues)* abiertos del proyecto o [generar uno nuevo](https://help.github.com/articles/creating-an-issue/) y subscribirse.
- Obtener una versión actualizada del código del proyecto clonando (*[clone](https://git-scm.com/book/es/v2/Fundamentos-de-Git-Obteniendo-un-repositorio-Git)*) el repositorio principal: 
  ~~~
  git clone https://github.com/Arquisoft/InciManager_e3b.git
  ~~~
   
- Activar (*[checkout](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Procedimientos-B%C3%A1sicos-para-Ramificar-y-Fusionar#_basic_branching)*) y crear una rama puntual (*[topic branch](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Flujos-de-Trabajo-Ramificados#_topic_branch)*) utilizando por ejemplo el nombre del desarrollador o del _issue_ correspondiente:
  ~~~
  git checkout -b topic-branch master
  ~~~
- Hacer la modificaciones necesarias (crear, editar, renombrar, mover o borrar ficheros) dentro del directorio de trabajo de la rama local.

- Confirmar (*[commit](https://git-scm.com/book/es/v2/Fundamentos-de-Git-Guardando-cambios-en-el-Repositorio)*) cuantos cambios sean necesarios utilizando el [formato de mensajes](#formato-mensajes-de-confirmaci-n) acordado.
  ~~~
  git commit -a -m 'Añade fichero README.md'
  ~~~

- Regularmente hacer el seguimiento (*[pull](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Ramas-Remotas#_tracking_branches)*) de la rama principal (_master_) fusionando (*[merge](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Procedimientos-B%C3%A1sicos-para-Ramificar-y-Fusionar#_basic_merging)*) cualquier novedad en la rama puntual y resolviendo cualquier [conflicto](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Procedimientos-B%C3%A1sicos-para-Ramificar-y-Fusionar#_basic_merge_conflicts) que pueda surgir en el proceso:
  ~~~
  git checkout master
  git pull 
  git checkout topic-branch 
  git merge origin/master
  git status
  ~~~

- Completadas las modificaciones necesarias, publicar (*[push](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Ramas-Remotas#_pushing_branches)*) el contenido de la rama local como rama remota (*[remote branch](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Ramas-Remotas#_remote_branches)*) en el repositorio principal.  
  ~~~  
  git checkout topic-branch
  git push --set-upstream origin topic-branch
  ~~~
  
- Una vez resueltos todos los conflictos, realizar una solicitud de incorporación (*[Pull Request](https://help.github.com/articles/creating-a-pull-request)*) del contenido de la rama puntual en la rama principal.

- En ausencia de objeciones por parte de otros contribuidores, [resolver la solicitud de incorporación](https://help.github.com/articles/merging-a-pull-request) uno mismo, fusionandolas directamente o reorganizando (*[rebase](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Reorganizar-el-Trabajo-Realizado)*) la secuencia de actualizaciones como considere oportuno.

- De forma opcional y una vez fusionada en la rama principal, es posible borrar (*[delete](https://git-scm.com/book/es/v2/Ramificaciones-en-Git-Ramas-Remotas#_delete_branches)*) la rama puntual.
  ~~~
  git push origin --delete topic-branch
  ~~~

- Añadir un nuevo comentario en el _issue_ correspondiente o cerrarlo definitivamente.

- Durante el proceso notificar cualquier novedad a través del [canal Gitter del proyecto](https://gitter.im/Arquisoft/InciManager_e3b).

## Formato mensajes de confirmación

~~~
Extracto de los cambios en 50 caracteres o menos

Texto explicativo más detallado, solo si es necesario. La línea en 
blanco que separa el asunto del resto del texto es crucial (a no ser 
que omitas el cuerpo y el mensaje solo tenga asunto); algunas 
herramientas pueden mostrar información alterada si omites la línea en
blanco de separación.

También se pueden añadir más párrafos, separados igualmente por una 
línea en blanco.

- Se pueden añadir listas
- Para las listas se usan guiones o asteriscos (como en Markdown)

En el pie, se pueden poner referencias a los ids de los issue trackers,
por ejemplo:

Resolves: #123
See also: #456, #789
~~~

### Asunto

- Tiene como objeto resumir en una única linea el motivo de la actualización.
- Debe comenzar con letra mayúscula y terminar sin punto.
- No debe exceder de los 50 caracteres.
- Debe estar escrito en tiempo verbal imperativo, ej: _"Merge branch 'topic-branch'"_. Como truco deberia poder concatenarse una frase antes del asunto y mantener el sentido original: _"If applied, this commit will_ **Merge branch 'topic-branch'**".

### Cuerpo

- Tiene como objeto definir el ***qué*** y el ***por qué*** del problema que resuelve el _"commit"_. El ***cómo*** ya esta incluido en el propio código de la actualización.
- Si el contenido y contexto del _"commit"_ se puede explicar en el asunto, no es necesario incluir un cuerpo.
- De incluirlo es necesario separarlo del asunto con una línea en blanco entre ambos.
- Tiene la estructura normal de varios parrafos, incluyendo frases terminadas con punto y la capacidad de incluir listas.
- El ancho de linea no debe exceder de los 72 caracteres.

### Pie

- Opcional, tiene como objeto incluir referencias a los _issues_ afectados o relacionados.

