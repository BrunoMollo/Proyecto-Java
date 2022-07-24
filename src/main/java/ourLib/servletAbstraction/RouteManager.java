package ourLib.servletAbstraction;

import java.util.HashMap;

import entities.Usuario;


class Pair<ENTITY>{
	public Operation<ENTITY> execution;
	public Integer permision;
	
	public Pair(Operation<ENTITY> e, Integer p) {
		execution=e;
		permision=p;
	}
}

public class RouteManager<ENTITY> {
	private final HashMap<String, Pair<ENTITY> > map = new HashMap<String, Pair<ENTITY>>();;
	

	public void setPath(String path, Operation<ENTITY> execution, Integer permision) {
		map.put(path, new Pair<>(execution, permision));
	}
	
	public Operation<ENTITY> getOperation(String path){
		return(obj, req, res)->{
			if(map.get(path).permision!=null) {
				Usuario user= (Usuario) req.getSession().getAttribute("usuario");
				if(user==null || user.getRol() < map.get(path).permision) {
					res.setStatus(401);
					return;
				}
			}
			
			map.get(path).execution.execute(obj, req, res);
		} ;
	}
	
	public Integer getPermision(String path){
		return map.get(path).permision;
	}
	
	public void setPermision(String path , Integer _permision){
		map.get(path).permision = _permision;
	}
	
	
	
}
