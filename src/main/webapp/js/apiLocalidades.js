
const $provincias = document.getElementById('selProvincia');
const $localidades = document.getElementById('selLocalidad');

	function compare_nombre( a, b )
  {
  if ( a.nombre.toLowerCase() < b.nombre.toLowerCase()){
    return -1;
  }
  if ( a.nombre.toLowerCase() > b.nombre.toLowerCase()){
    return 1;
  }
  return 0;
}

if(!$provincias.value){
	$provincias.addEventListener("DOMContentLoaded",provincia);
	
	}
else{
	$provincias.addEventListener("click",provincia,{'once':true});
	
	
}

$provincias.addEventListener("change",e => localidad(e.target.value))
 function provincia() {
    fetch("https://apis.datos.gob.ar/georef/api/provincias")
    .then(res => res.ok ? res.json(): Promise.reject(res))
    .then(json => {
		let $options= "<option disabled selected >Elige una Provincia </option>"; ;
		
		let ordenado = json.provincias.sort(compare_nombre)
		for(let prov of ordenado)
		{		
        $options += `<option value="${prov.nombre}">${prov.nombre}</option>`; 
    	} 
   		 $provincias.innerHTML = $options;
 
})}
 function localidad(prov) {
    fetch(`https://apis.datos.gob.ar/georef/api/localidades?provincia=${prov}&max=5000`)
    .then(res => res.ok ? res.json(): Promise.reject(res))
    .then(json => {
		let $options = "<option disabled selected >Elige una Localidad </option>";
		let ordenado = json.localidades.sort(compare_nombre)
		for(let loc of ordenado)
		{		
        $options += `<option value="${loc.nombre}">${loc.nombre}</option>`; 
    	} 
   		 $localidades.innerHTML = $options;
 
})}



	


