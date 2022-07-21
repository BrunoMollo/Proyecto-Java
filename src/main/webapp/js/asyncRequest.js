function sendRequest(evento){
		let input=evento.target;
		let div= input.parentNode;
		let sugerencias=div.querySelector("ul");
	
		if(input.value.length<3){return}
	
	 	let url=div.getAttribute("url");
	 	let searchParameter=div.getAttribute("searchParameter");
	 	
		axios.get(url+"?"+searchParameter+"="+input.value)
		.then((res)=>{
			
			sugerencias.textContent = ''; //borra lo que hay en sugerencias
			
			for(let i=0; i<res.data.length; i++){
				let li=document.createElement("li");
				li.innerText=res.data[i][searchParameter];
				li.addEventListener('click', ()=>{
					evento.target.value=li.innerText
					sugerencias.textContent = '';
				});
				sugerencias.appendChild(li);	
			}
			
		})
		.catch((err)=>console.log(err));
	}

	//Agrega el Listener al cargar la pagina en el inicio
	
	let arr=document.querySelectorAll(".async-search");
	arr.forEach((elemnt)=>elemnt.addEventListener('keyup', sendRequest));
	
	
	
	