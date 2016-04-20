$(".glyphicon-ok").closest(".alternativa").each(function(){
	if($(this).find("input").is(":checked")){
		$(this).closest(".questao").addClass("questao-correta");
	}else{
		$(this).closest(".questao").addClass("questao-errada");
	}
});
