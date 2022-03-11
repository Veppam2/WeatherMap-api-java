# proyecto-01
Primer proyecto de Modelado y Programación semestre 2022-2: Web Services

Instrucciones de ejecución:
En la carpeta Tarea01/proyecto ejecutar "mvn compile assembly:single". Esto generará un archivo dentro de la carpeta Tarea01/proyecto/target llamado "Proyecto01-jar-with-dependencies.jar"

Después basta con ejecutar:
mvn install para correr las pruebas 

y para probar el sistema
java -jar ./target/Proyecto01-jar-with-dependencies.jar [Entrada de archivo .csv]

Hay un ejemplo del archivo llamado "dataset1.csv", con él se pueden hacer pruebas

En caso de Ejecutar el .jar sin el archivo por parámetros, se obtendrán los datos de los vuelos con sus respectivos climas desde la caché o se hará una nueva búsqueda en caso de que hayan pasado 5 horas de la última consulta.


