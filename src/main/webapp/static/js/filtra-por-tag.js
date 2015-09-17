
$(".questao").each(function(){
	var tags =	$(this).find(".tags");
	var retiraUltimaVirgula = tags.text().slice(0,-1);
	tags.text(retiraUltimaVirgula);
});

function arrayContemOutroArray(arrayParaBuscar, arrayBuscado){
	  for(var i = 0; i < arrayParaBuscar.length; i++){
	    if(arrayBuscado.indexOf(arrayParaBuscar[i]) === -1)
	       return false;
	  }
	  return true;
	}



$("#busca").on("input",function(){
	if($(this).val().length == 0){
		$(".questao").fadeIn();
	}else{
		$(".questao").fadeOut().find(".tags").filter(function(){
			var tags = $(this).text().split(",");
			var filtro = $("#busca").val().split(",");
			var existeTags = arrayContemOutroArray(filtro,tags) ;
			return existeTags;
		}).closest(".questao").fadeIn();
	}
});

