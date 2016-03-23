var provas = [];
$('.prova').each(function(){
	var $this = $(this);
	provas.push($this.text().trim());
});
console.log(provas.join(""));


$('#lista-de-provas li').hide();

$("#busca").on("keyup click input",function(){
	if(this.value.length > 0){
		$('#lista-de-provas li').hide().filter(function() {
			return $(this).text().toLowerCase().indexOf($("#busca").val().toLowerCase()) != -1;
		}).show();
	}
	else{
		$('#lista-de-provas li').hide();
	}
});