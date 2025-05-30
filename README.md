# Pasos para ejecutar este proyecto
Este proyecto se puede ejecutar mediante Docker, para ello es necesario ejecutar **mvn clean install** para que 
se pueda generar el archivo jar que se va a cargar.
También es importante recalcar que el archivo *localDatabase.sql* contiene la creación
de la tabla de la base de datos, por lo que es necesario tenerlo.

Una vez teniendo el archivo .jar generado en la carpeta target ejecutamos:
```
docker-compose up --build
```

Lo anterior generará las imágenes de nuestro aplicativo y de nuestra base de datos. 
El puerto para probar la API es 8080.
Una vez levantado el servicio es posible probar en este puerto mediante la colección de postman 
También es importante resaltar que el docker-compose tiene variables de entorno por lo que si se quiere probar sin usar docker es necesario establecer dichas variables.


## ¿Qué pasos tomé para aprender Swagger?
Swagger resultó ser una librería bastante sencilla de utilizar, al menos para lo requerido en este proyecto, lo que se
necesitó no resultó difícil de comprender ni de implementar, lo anterior se consiguió mediante los siguientes pasos:
1. Entendimiento general: Era necesario entender QUÉ era Swagger y para qué se utilizaba, de esta forma se acudió a la web oficial así como a otros sitios dedicados a la explicación de tecnologías así también como asistentes de inteligencia artificial para tener una base sólida de qué era y para qué se utilizaba
2. Entendimiento de la implementación: Una vez que comprendido el funcionamiento de Swagger era necesario saber qué tenía que esperar, por lo que también se acudió a videos buscando temas más particulares gracias al entendimiento general obtenido anteriormente, es por eso que los videos son una buena fuente de información porque se muestra la forma en la que se implemente y como es el resultado de esa implementación
3. Implementación: Una vez comprendida la base y los puntos particulares solo era necesario realizar la implementación en este proyecto, ya que es muy diferente la teoría a la práctica, y conforme se avanzaba salían temas que se tenían que abordar modificando código o en dado caso buscando más respecto a la librería para poder realizar el comportamiento deseado.

## ¿Cómo resolviste dudas sobre Docker?
Docker es una tecnología que ya había utilizado anteriormente aunque solamente por temas de aprendizaje, por lo que recordar lo básico no tomó mucho tiempo, sin embargo, las implementaciones particulares se abordaron de la siguiente manera.
1. ¿Cómo se relaciona dockerfile con docker-compose? Esta pregunta fue necesaria, ya que lo que conocía era Dockerfile, pero no docker-compose, además de que si quería abordar el tema de la base de datos era necesario comprender docker-compose, por lo que se realizaron búsquedas en la web, artículos y/o videos que explicaran como relacionar el Dockerfile con la base de datos en docker.compose
2. ¿Cómo implementar mis tablas en la base de datos generada por docker-compose? Este punto es importante debido a que la forma en la que estoy acostumbrado a trabajar es realizando primero una base de datos con su respectivo usuario y tablas, para posteriormente mapearlas en Java y si bien tengo el conocimiento de que las entidades mapeadas se pueden crear las tablas de la base de datos si es necesario, no es un método que me guste, por lo que era necesario saber como "personalizar" la base de datos creada. Es así como se relaciona el archivo localDatabase.sql, con él se sigue el flujo al cual estoy acostumbrado incluso en una imagen de Docker. Esto se realizó mediante la búsqueda de este punto con este mismo planteamiento así de particular.

## ¿Qué desafíos encontraste?
Los desafíos afrontados en el desarrollo de esta prueba se listan a continuación
1. Cambio en la metodología de trabajo: La metodología de trabajo requerida para el desarrollo de esta prueba difiere bastante con lo que estoy acostumbrado a realizar en mi actual trabajo, pero no es algo que no haya realizado anteriormente, por lo que cambiar ese ritmo y la forma de implementar las cosas fue un poco lento de realizar, pudiendo decir que la gran mayoría del tiempo se fue en adaptarme nuevamente a este modo.
2. Conflictos de dependencias: Debido a que anteriormente había realizado cosas similares tengo proyectos de los cuales me basé para realizar esta prueba y un punto que hice fue copiar y pegar las dependencias que utilicé en otros proyectos y que sabía que tenía que utilizar en este proyecto, sin embargo, para el caso de jakarta tenía especificada una versión en particular que no me permitía realizar correctamente el mapeo del Id en la base de datos de tipo UUID, por lo que pase buen rato buscando qué pudiera ser y aunque no encontré nada que me dijera que se debía a jakarta, toda la información rescatada me dio elementes para **YO** poder determinar que el conflicto entraba por la versión de jakarta. Una vez retirada la versión no hubo más problemas.
3. Primer enfoque muy simple: En mi intento de realizar lo más sencillo posible esta prueba, me encontré que el objeto de la request y response variaban ligeramente, algo que al momento de documentar en swagger entraba en conflicto, por lo qeu tuve que agregar un DTO específico para la request y response y no reutilizar el mismo.
4. Implementación de seguridad sin usuarios: Nuevamente en mi intento de realizar la prueba de manera sencilla, me enfrenté al desconocimiento de como generar un token sin tener tablas relacionadas con los usuarios algo que ya había implementado anteriormente, pero que sin los usuarios me causo problemas, pero al realizar la búsqueda de este tema tan particular pude encontrar la respuesta aunque quizá no de la mejor manera.
5. Acceso a Swagger mediante token: Una vez implementado el token, debido a que todas las peticiónes tenían que ser autenticadas a excepción de la que generaba el token, me enfrenté a que también pedía autenticación para la documentación de swagger, por lo que tuve que invertir la condición de modo que acepte todo y solo pida autenticación para tasks/, esto para este ejercicio está bien, pero en caso de tener otros elementos sería algo malo, sin embargo, no pude hacer que permitiera todo de swagger.

## ¿Qué mejoraría si tuviera más tiempo?
Creo que se manejó correctamente el desarrollo, sin embargo, hay varios detalles que se pueden mejorar, como la homologación de las respuestas de error. En este caso las respuestas de error se manejan cuando los campos no se respetan, es decir, cuando la fecha no trae formato de fecha y cuando el status no traer un valor valid dentro del enum definido, pero, si un campo requerido como title o status no viene, el error que regresa es el generado por default por la aplicación. 
También considero que la separación de la lógica es muy importante, ya que el service está enfocado en el objeto Entity mapeado, cuando CREO que debería ser enfocado en el DTO dejando la lógica en la capa de servicio y no en el controlador, de hecho este punto fue un punto que no me permitió realizar pruebas unitarias más extensas debido a que se manejaba el Task cuyas propiedades interferían con ciertas pruebas, por ejemplo, validar que los cambios de fecha y status fueran válidos, sin embargo, en el entity ya vienen como fecha y como enum por lo que no había forma de forzarlos que no fueran correctos como si es posible en request.
