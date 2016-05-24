var url = document.URL;
var endUrl = url.split("/").pop();

if(endUrl == "login" || endUrl == "efetuaLogin" || endUrl == "cadastro"){
	document.getElementById("logout").style.visibility = 'hidden';
}