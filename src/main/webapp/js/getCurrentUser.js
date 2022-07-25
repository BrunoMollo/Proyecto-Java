const $=(query)=>document.querySelector(query);

function putDataInDOM(data){
	$("#show-user-name").innerText="USER: "+data.user;
	$("#show-user-rol").innerText="ROL: "+data.rol;
}


axios.get('/lafarmacia/login')
.then((res)=>putDataInDOM(res.data))
.catch((err)=>console.log(err))
