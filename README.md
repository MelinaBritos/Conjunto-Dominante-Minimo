Un conjunto dominante en un grafo es un conjunto A de vertices tal que todo vertice que no
esta en A tiene un vecino en A. El problema de encontrar un conjunto dominante de menor
tamaño posible se llama el problema de conjunto dominante mınimo, y su version de decision
es NP-completa. El trabajo practico consiste en implementar un algoritmo goloso para este
problema.
Se debe implementar una aplicacion que, a partir de los datos del grafo, utilice un algoritmo
goloso para encontrar un conjunto dominante de tamaño tan pequeño como pueda. La aplicacion debe informar el conjunto obtenido.

En el siguiente informe se detalla el desarrollo del trabajo práctico Conjunto
Dominante Mínimo de programación 3. El trabajo consta de la implementación
de un algoritmo goloso para el problema junto a una interfaz en la cual se
permita ingresar un grafo sobre el cual se busque el conjunto.

● En este caso decidí implementar el patrón de diseño “Forms and
controls”.

Decisiones de interfaz

La interfaz se divide en dos clases: View y View Grupos. La clase View como la
interfaz principal permite el ingreso manual del grafo, con una opción para crear
el grafo con su cantidad de vértices. Si al momento de ingresar la cantidad de
vértices se recibe un número no válido como 0, 1 o letras, el botón “Crear
Grafo” captura el valor invalido y muestra una mini ventana al usuario
informando que debe ingresar una cantidad de vértices válida. Luego de ser
creado el grafo se habilita la opción de agregar aristas con los valores posibles
de los vértices, en caso de intentar agregar una arista sin crear el grafo primero,
agregar un loop o agregar una arista ya existente se le informa al usuario a
través de una ventana. Luego de ser creado el grafo, se permite presionar el
botón “Calcular Conjunto” el cual al ser seleccionado ejecuta la interfaz
“ViewGrupos” la cual contiene dos paneles en los cuales se pueden visualizar
el grafo ingresado y su conjunto dominante mínimo. para la visualización del
grafo se utilizó la librería “JGraphx” que pasándole los datos del grafo y los
datos del conjunto dominante mínimo los gráfica en los paneles. acerca de la
segunda interfaz, no pude resolver la superposición de los vértices por lo cual si
el grafo ingresado tiene un gran tamaño la visualización no se ve limpia. Luego
de los paneles se encuentra un botón “Volver” el cual permite regresar a la
interfaz principal para volver a ingresar un grafo nuevo.

Decisiones lógicas

Las clases de negocio del trabajo práctico se dividen en tres clases: Grafo,
Model y AlgoritmoGoloso. La clase grafo es implementada sobre lista de
vecinos. La clase Model es la clase llamada por la interfaz al momento de
realizar acciones como crear el grafo, agregar sus aristas y calcular el conjunto
dominante mínimo. La clase “AlgoritmoGoloso” es la clase que realiza el
algoritmo goloso para obtener el conjunto dominante mínimo de un grafo. La
lógica usada para resolver el problema es la siguiente: la clase contiene como
datos: un grafo, un comparator y un conjunto “alcanzados”, este es un objeto
que al ser creado debe recibir estos datos. El comparator que decidí utilizar es
un comparador que ordena los vértices según la cantidad de vecinos que tenga,
de mayor cantidad de vecinos a menor cantidad de vecinos. El método
“resolver” crea una lista llamada solución la cual sería el conjunto dominante
mínimo obtenido. Luego se recorren los vértices ordenados por cantidad de
vecinos y para saber cuáles vértices agregar se utiliza el conjunto de alcanzados
en el cual se guardan los vértices que son alcanzados por el conjunto dominante
mínimo, hasta que alcanzados no tenga el mismo tamaño del grafo se verifica si
los vecinos de un vértice contiene un vértice todavía no alcanzado y en ese caso
se lo agrega. también, contempla los vértices aislados añadiendo los vértices sin
vecinos.
