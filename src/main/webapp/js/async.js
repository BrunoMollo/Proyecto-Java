
	let arr=document.querySelectorAll(".async-search");
	arr.forEach((elemnt)=>{elemnt.addEventListener('keyup', sendRequest)
						elemnt.addEventListener('click', sendAll);
							;
	});
	let input= document.getElementById("asyncInput");
	let div= input.parentNode;
	let sugerencias=div.querySelector("ul");
	let url=div.getAttribute("url");
	let searchParameter=div.getAttribute("searchParameter");


 function sendAll(evento) {
    axios.get(url+"all")
    	.then(res =>{
			 sugerencias.textContent = '';		
			  
			 for(let i=0; i<res.data.length; i++){
				let li=document.createElement("li");
				li.innerText=res.data[i][searchParameter];
				li.addEventListener('click', ()=>{
					evento.target.value=li.innerText
					sugerencias.textContent = '';
				});
				sugerencias.appendChild(li);	
			 }})
			 .catch((err)=>console.log(err));
	}	
function sendRequest(evento){
		if(input.value.length<3){return}
		axios.get(url+"getbyname?"+searchParameter+"="+input.value)
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


	
	