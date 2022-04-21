# CUU 02 Comprar medicamento receta
**Objetivo**: Comprar medicamento con receta medica
**Acotor Principal**: Cliente
**Iniciador**: Responsable de venta
**Actores extra**: Validador de Obra Social

**Precondiciones**: Cliente tiene consigo la receta correspondiente al remedio y su carnet de mutual

**Postcondiciones**:  
    + **Exito**:Se registra venta y modifica stock  
    + **Exito alternativo**:*vacio*\(posible encargue de medicamento, ver 2.b\)  
    + **Fracaso**:*vacio*  

### Flujo basico:
1.	Cliente se presenta y solicita remedio presentando la receta  
2.	Responsable checkea carnet de mutual chocandolo con el DNI y lo ingresa al validador correspondiente, el cual requiere el ingreso del plan  
3.	Responsable indica al sistema droga o nombre comercial que quiere el cliente, Sistema muestra remedios y sus respectivos tamaños y precios, los que estan en stock aparecen destacados    
*Esto se repite por cada remedio que requiera el cliente*
4.	Responsable cierra venta, cobra al cliente, sistema emite ticket y modifica el stock del producto  

### Flujo alternativo:
2.a Validador informa que la receta es invalida o que no es aceptada:  
>>2.a.1 Sistema informa

3.a Sistema no encuentra remedio en stock  
>>3.a.1 Sistema informa la situacion , FCU \(posible encargue de medicamento\)
 
4.a Error de pago o falta de fondos:  
>>4.a.1 Sistema informa y plantea alternativas  

##### Reglas de Negocio
* Las recetas tienen un vencimiento de 30 dias desde que se prescribieron  

---

##### Notas
- Fecha prescripcion no necesariamente es igual a la fecha de venta  
- Existe un historial de precios de listas por cada proveedor  

##### Diccionario de Datos 
Receta=fecha_prescripcion+nro_afiliado+nombre_OS+1{nombre_remedio+cantidad}cant_max_OS+nro_matricula_medico  
Ticket=1{nombre_remedio+cant+descuento+precio_total}n+nro_afiliado+nombre_mutual  
