# Proyecto1SO


Planteamiento del Problema 
El objetivo de este proyecto es que los estudiantes desarrollen un simulador que les permita comprender y aplicar conceptos fundamentales de la planificación de procesos en sistemas operativos. A través del desarrollo de este simulador, se espera que los estudiantes analicen y encuentren la configuración óptima del sistema en función de dos factores clave: precio y rendimiento. Esto incluirá la gestión de procesos, el manejo de excepciones y la implementación de diversas políticas de planificación.
El simulador deberá permitir la creación de procesos por parte del usuario, quienes definirán características como el nombre del proceso, la cantidad de instrucciones y, CPU bound (Muy consumidor del procesador) y I/O bound (Muy consumidor de E/S). Si un proceso es I/O bound, deberá especificarse cuántos ciclos se necesitan para generar una excepción y cuántos para satisfacerla. Además, el sistema debe incluir opciones de configuración como la duración de un ciclo, y el número de procesadores activos (entre 2 y 3). Estos parámetros podrán ser modificados tanto al inicio como durante la ejecución de la simulación, los cuales deberán de ser guardados en un txt, csv, json (lo que les sea más conveniente) para que sean cargados en la ejecución inicial del proyecto.
La simulación en sí debe proporcionar una visualización clara de los estados de los procesos, mostrando información esencial como el estado actual de las colas de listos y bloqueados, y los datos de cada PCB (incluyendo valores de registros, ID y nombre del proceso). Así cómo también se debe mostrar el proceso que se está ejecutando en el momento, y la instrucción que está por ejecutarse en el program counter. Además, debe ser posible alternar entre diferentes políticas de planificación, como FCFS, SJF y Round Robin, para el cual se debe estar ejecutando el sistema operativo. 
Como parte de un módulo adicional para quienes busquen puntos extra, el simulador podrá incluir gráficas que ilustran el rendimiento del sistema a lo largo del tiempo, así como el rendimiento individual de cada CPU. Estas gráficas proporcionarán una representación visual que permita comprender mejor la eficiencia del sistema bajo diferentes configuraciones. Pueden escoger la medida de rendimiento que parezca más conveniente y apropiada. 
El simulador debe indicar en todo momento si se está ejecutando el sistema operativo o un programa de usuario. Asimismo, debe visualizar de forma clara cómo los procesos entran y salen de las listas de "listos" y "bloqueados".
Es importante destacar que el simulador debe ser lo suficientemente flexible para permitir la configuración dinámica de ciertos parámetros, como la duración de los ciclos y el número de CPUs activos, durante la ejecución. Además, las configuraciones realizadas deben poder guardarse para ser cargadas en futuras ejecuciones. El sistema operativo del simulador deberá gestionar los cambios de procesos y atender excepciones de manera eficiente.
Es importante recordar que dependiendo del tipo de consumo del procesador de un proceso, este puede verse beneficiado o no por el algoritmo de planificación, por lo que esto debe ser considerado. 
En cuanto a las métricas de rendimiento, se espera que el simulador registre tanto los promedios generales del sistema como los resultados individuales de cada CPU. Esto permitirá a los estudiantes evaluar el impacto de sus decisiones y ajustar las configuraciones para mejorar el rendimiento.

Requerimientos funcionales
Deben hacer uso de Hilos/Threads para la simulación de los procesos y Semáforos/Semaphores para garantizar exclusión mútua.
Se deben desarrollar al menos 5 de las 6 políticas de planificación que se muestran al final del documento. El ordenamiento de la cola posterior a la selección deberá de ser programado por ustedes.
Se debe hacer uso de una interfaz gráfica que permita observar  durante la simulación en tiempo de ejecución los estados de:
Cola de procesos del sistema, tanto de procesos listos cómo de procesos bloqueados. Incluyendo una lista de los procesos culminados
Valor del program counter, procesadores activos, y que proceso se está ejecutando en cada procesador.
Los siguientes elementos del PCB por proceso (tanto en las colas como en el CPU):
ID (generado dinámicamente y único)
STATUS: Running, Blocked, Ready
Nombre
Estado del PC 
Estado del MAR
Selector de tipo de algoritmo de planificación, e indicador de cual tipo de planificación se está usando a tiempo real. 
Si el sistema operativo se está ejecutando o se encuentra siendo ejecutado un proceso de usuario por CPU. 
El número de ciclo de reloj global dentro de la simulación desde que se inicia.
La simulación debe permitir en tiempo de ejecución:  
Intercambiar los tipos de algoritmos de planificación de procesos.
La duración de un ciclo de ejecución. (en segundos o ms)
Desde la interfaz se le debe poder indicar al programa los siguientes  parámetros, para que sean escritos en un archivo (TXT, CSV o JSON) y  utilizados en futuras simulaciones:  
Funcionamiento General de la simulación
Duración del ciclo de ejecución de una instrucción. 
Número de procesadores activos
Carga de procesos
Número de instrucciones por programa, o longitud. 
Si el proceso es CPU bound o I/O bound.
El número de ciclos para realizar una excepción (para que el proceso haga una solicitud de E/S)
El número de ciclos en el que se completa la solicitud de dicha excepción. 

Bono 10% (2 puntos): Mostrar en un mismo gráfico la utilidad con respecto al tiempo de  los estudios del rendimiento por CPU y por sistema. Puede hacerse  uso de librerías de gráficos para Java  

NOTA: Con el fin de minimizar la complejidad del proyecto y estandarizar. Se debe asumir que:
Todas las instrucciones se ejecutan en un único ciclo de instrucción.
Por simplicidad, en este proyecto, todos los procesos se ejecutan de manera lineal. Eso quiere decir que el PC y el MAR incrementarán una unidad por cada ciclo del reloj. 
La asignación de procesos será de manera dinámica, por lo que habrá una única cola de listos para todos los procesadores.
Se debe implementar SMP para el llamado del SO generado por excepciones.
El tratamiento de las excepciones debe realizarse con el uso de Threads, y cada “Thread” de excepción debe regresar al procesador en donde fue generado. 	
Consideraciones
El proyecto puede ser elaborado máximo por 2 personas (3 si alguno queda  solo) 
Se permiten proyectos de compañeros de diferentes secciones. 
Solo se permite el uso de librerías para presentar la gráfica y leer el CSV, JSON, SQL, etc.
No se permite uso de librerías para estructuras de datos, como ArrayList, Queue, etc.
Tener un repositorio en GitHub es obligatorio. 
Para la entrega, junto al código, se debe entregar un informe donde se detalle la funcionalidad de las clases y métodos más importantes del  proyecto junto a las conclusiones de las configuraciones para cada planificación, no hace falta documentar todo el código. 
La entrega del trabajo consta de el informe en .PDF y el link de github. Se deberán enviar ambos a sleon@correo.unimet.edu.ve y andres.martin@correo.unimet.edu.ve antes de las 7:00 am del viernes de semana 7.
Se requiere que hagan el proyecto en versiones posteriores a java 21, para poder asegurar un manejo adecuado del uso de los repositorios tanto entre los miembros del equipo como en la corrección.
El viernes semana 7, se realizará un interrogatorio por turnos en  el salón habitual. Todos los integrantes del equipo deben estar presentes  (preferiblemente con alguna de sus computadoras).
Los proyectos sin interfaz gráfica, serán calificados en base a 0 (cero). No se corregirá código para validar funcionamiento, más si para verificar. Que los miembros del equipo lo comprendan.
Los proyectos sin repositorio en GitHub, serán calificados en base a 0 (cero). 
Los alumnos que no asistan al interrogatorio, serán calificados en base a 0  (cero).
Los programas que “no corran”, serán calificados en base a 0 (cero).
Los proyectos que no sean realizados en Java (específicamente en el IDE de Netbeans) serán calificados en base a 0  (cero) .
Es importante que cada uno de los miembros de cada equipo demuestre un buen conocimiento general del funcionamiento de cada módulo de la solución, y una profunda capacidad explicativa de las tareas que realizó. En caso de no ser capaz de defender su trabajo, el preparador penalizará según la gravedad y su criterio.
Información Teórica
Asignación dinámica: Existe una cola de listos común, y cada vez que se vaya a despachar un proceso se asigna un procesador para que se ejecute, con eso en cuenta, un proceso puede pasar por varios procesadores hasta finalizar su ejecución. Ventajas, carga de trabajo distribuida de forma más uniforme entre procesadores. Desventajas, múltiples operaciones de asignación. Nota, esto es posible gracias a que los contextos de ejecución de los procesos se guardan en sus PCB, ya que se puede recuperar el contexto independientemente del procesador donde se ejecute

SMP en referencia a la planificación de los multiples procesadores: El SO operativo puede ser ejecutado en cualquier procesador, y cada procesador se auto planifica para escoger los procesos de usuario, el SO debe garantizar que esa escogencia no sea conflictiva, como que dos procesadores escojan el mismo proceso
