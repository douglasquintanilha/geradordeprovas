$(function() {
	var campo = $("#tags").val();
    campo = campo.replace("[","").replace("]","");
    $("#tags").val(campo);
});