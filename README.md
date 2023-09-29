# WeatherMap-api-java
OpenWeather's web services api used in simple java project.

### Requirements
* <a href="https://openweathermap.org/appid" target="_blank"> Weather Map api's credentials </a>. It has to be given as value of `WD_ID` in `Tarea01/proyecto/.env` file. Expample can be found in `Tarea01/proyecto/.env.exmple` file.
* Java and <a href="https://maven.apache.org/" target="_blank"> Maven</a> installed 

### Use
In `Tarea01/proyecto` directory execute:
```
mvn compile assembly:single
```
this will generate the `Proyecto01-jar-with-dependencies.jar` file.

You can run tests with:
```
mvn install
```
and use the system with:
```
java -jar ./target/Proyecto01-jar-with-dependencies.jar [file.csv]
```
`file.csv` is opcional. We give a dataset test case in `Tarea01/dataset1.csv`.

In case of ececuting .jar file without file as parámeter we pull flight data from caché if execution of .jar has been in less than 5 hours since last call. 
