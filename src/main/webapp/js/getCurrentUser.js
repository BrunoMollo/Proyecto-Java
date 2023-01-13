
function putDataInDOM(data){
	document.querySelector("#show-user-name").innerText="USER: "+data.user;
	document.querySelector("#show-user-rol").innerText="ROL: "+data.rol;
}

fetch('/lafarmacia/login/who')
.then((res)=>putDataInDOM(res.data))
.catch((err)=>console.log(err))
