var url = document.URL;
var endUrl = url.split("/").pop();

if(endUrl == "loginForm"){
	document.getElementById("link-logout").style.visibility = 'hidden';
}