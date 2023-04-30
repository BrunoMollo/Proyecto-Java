# Proyecto-Java

#### 3.2.1 Checklist

##### Regularidad

|Requerimiento|cant. mín.<br>1 o 2 integ|cant. máx.<br>3 o 4 integ|Detalle/Listado de casos incluidos|
|:-|-:|-:|:-|
|ABMC simple|1 x integ|1 x integ|Genericos/Drogas, Obra social
|ABMC dependiente|1|2| Medicamento, Cliente
|CU NO-ABMC|1|2| Venta de medicamento
|Listado simple|1|3(*)| Informe de ventas del mes
|Listado complejo|0|1(*)|

(\*) los grupos de 3 y 4 integrantes deben elegir entre 1 listado complejo o 3 simlples para regularizar.


##### Aprobación Directa

|Requerimiento|cant. mín.<br>1 o 2 integ|cant. máx.<br>3 o 4 integ|Detalle/Listado de casos incluidos|
|:-|-:|-:|:-|
|ABMC|todos|todos|Genericos/Drogas, Obra social
|CU "Complejo"(nivel resumen)|1|2|Venta de medicamento por obra social
|Listado complejo|1|2| Listado de ventas de Obra Social
|Nivel de acceso|2|2| Vendedor y Admin
|Manejo de errores|obligatorio|obligatorio|no requiere detalle|
|requerimiento extra obligatorio (**)|0|1|
|publicar el sitio|olbigatorio|obligatorio|no requiere detalle|

(\*\*) sólo grupos de 3 y 4 integrantes


###### Requerimientos extra - AD
|Requerimiento |Detalle/Listado de casos incluidos|
|:-|:-|
|Manejo de archivos||
|Custom exceptions||
|Log de errores||
|Envio de emails||


#### Modelo del Dominio

![Image text](https://github.com/BrunoMollo/Proyecto-Java/blob/main/Artefactos%20Analisis/MD%20Farmacia.drawio.png)
