function sendRequest(evento){
		let input=evento.target;
		let div= input.parentNode;
		let sugerencias=div.querySelector("ul");
	
		if(input.value.length<2){
			sugerencias.textContent = ''; //borra lo que hay en sugerencias			
		}
	
		if(input.value.length<3){
			return
		}
	
	 	let url=div.getAttribute("url");
	 	let searchParameter=div.getAttribute("searchParameter");
	 	
		fetch(url+"?"+searchParameter+"="+input.value)
		.then(res=>res.json())
		.then((data)=>{
			
			sugerencias.textContent = ''; //borra lo que hay en sugerencias
			
			for(let i=0; i<data.length; i++){
				let li=document.createElement("li");
				li.innerText=data[i][searchParameter];
				li.addEventListener('click', ()=>{
					evento.target.value=li.innerText
					sugerencias.textContent = '';
				});
				sugerencias.appendChild(li);
				console.log(li)	
			}
			
		})
		.catch((err)=>console.log(err));
	}

	//Agrega el Listener al cargar la pagina en el inicio
	let arr=document.querySelectorAll(".async-search");
	arr.forEach((elemnt)=>elemnt.addEventListener('keyup', sendRequest));
	
	
	
	