$(document).ready(function () {
	$("#lista-questoes li").hide();	
	$("#busca").on("keyup click input", function () {
		if (this.value.length > 0) {
			$("#lista-questoes li").hide().filter(function () {
				return $(this).text().toLowerCase().indexOf($("#busca").val().toLowerCase()) != -1;
			}).show();
		}
		else {
			$("#lista-questoes li").hide();
		}
	});
});