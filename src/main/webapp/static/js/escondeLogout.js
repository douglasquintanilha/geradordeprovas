var url = document.URL;
var endUrl = url.split("/").pop();

if(endUrl == "login" || endUrl == "efetuaLogin"){
	document.getElementById("logout").style.visibility = 'hidden';
}