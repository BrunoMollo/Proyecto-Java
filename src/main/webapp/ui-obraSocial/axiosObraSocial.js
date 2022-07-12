

//VER EN MAS DETALLE DESPUES
function processError(error, log){
	if (error.response) {
      // La respuesta fue hecha y el servidor respondió con un código de estado
      // que esta fuera del rango de 2xx
      log.innerText="Peticion no aceptada"; 
      console.log(error.response.data);
      console.log(error.response.status);
      console.log(error.response.headers);
    } else if (error.request) {
      // La petición fue hecha pero no se recibió respuesta
      // `error.request` es una instancia de XMLHttpRequest en el navegador y una instancia de
      // http.ClientRequest en node.js
      log.innerText="Servidor caido"; 
      console.log(error.request);
    } else {
      // Algo paso al preparar la petición que lanzo un Error
      log.innerText="No se pudo hacer la peticion"; 
      console.log('Error', error.message);
    }
}


function updateObraSocial(cod) {
		newName= document.getElementById(`name-${cod}`).value;
		newTel= document.getElementById(`tel-${cod}`).value;
		newMail= document.getElementById(`email-${cod}`).value;
		newDesc= document.getElementById(`discount-${cod}`).value;
		
		log= document.getElementById(`logger-${cod}`);
		log.innerText="Procesando...";
		axios.put(window.location , 
			{
		    	id: cod,
		    	nombre: newName,
			    telefono: newTel,
			    email: newMail,
			    discount: newDesc
		    }    
		)
		 .then( (response)=>log.innerText="guardado!" )
		 .catch((error) =>	processError(error,log) )
		 .then( ()=>{ setTimeout(()=>log.innerText="",1000) } );	 
		 
}