package ourLib.servletAbstraction;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
*<p>
* Esta Interfaz define la forma de las expresiones lambda que manejan
* las ejecuciones de las distintas subUrls del servlet.
* Recibe como parametro la request y la response ademas de un objeto
* generico que es mapeado a travez de los datos recibidos de la request
* (se debe definir este mapeo en el metodo getEntityFromRequest )
*</p>
*/
@FunctionalInterface
public
interface Operation<T>{
	void execute(T obj,HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}