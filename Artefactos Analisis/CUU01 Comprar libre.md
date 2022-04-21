# CUU 01 Comprar medicamento libre
**Objetivo**: comprar remedio de venta libre
**Actor principal**: Cliente
**Iniciador**: Responsable de venta

**Precondiciones**:*vacio*
**Postcondiciones**:
    **Exito**:Se registra venta y modifica stock
    **Exito alternativo**:*vacio*\(posible encargue de medicamento, ver 2.b\)
    **Fracaso**:*vacio*

#### Flujo basico:

1.	Cliente se presenta y solicita remedio,
2.	Responsable indica al sistema droga o nombre comercial que quiere el cliente, sistema muestra remedios y sus respectivos tamaños y precios, los que estan en stock aparecen destacados
*Esto se repite por cada remedio que requiera el cliente*
3.	Cuando el Cliente pago, el Responsable indica al sistema el cierre del la venta;  sistema registra venta, emite ticket y modifica el stock del producto


#### Flujo alternativo:
2.a Sistema no encuentra remedio:
    2.a.1 Sistema informa la situacion, FCU
2.b Sistema no encuentra remedio en stock:
    2.b.1 Sistema informa la situacion, FCU\(posible encargue de medicamento\)
3.a Error de pago o falta de fondos
    3.a.1 Sistema informa y plantea alternativas

##### Notas
2.b.1 Luego de que se informe el faltante, el vendedor ingresa a la pagina correspondiente del proveedor y añade al pedido el producto faltante, si esta no lo tiene disponible, se buscan proveedores alternativos.

Los proveedores de un remedio pueden ser tanto los laboratorios como las droguerias 
El sistema debe generar un listado de reposicion con las ventas diarias para que despues el vendedor haga la reposicion mencionada^
EL sistema debe comprobar que no haya faltante de existencias por debajo de un margen
Puede ser que haya un menu de resrva de medicamentos por falta de stock