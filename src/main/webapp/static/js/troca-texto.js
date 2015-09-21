var texto_esconder = "Esconder Alternativas";
var texto_exibir = "Exibir Alternativas";

$(".botao-exibir-alternativas").one("click",esconderAlternativas);

function esconderAlternativas(){
	$(this).text(texto_esconder);
	$(this).one("click",exibirAlternativas);
}
function exibirAlternativas(){
	$(this).text(texto_exibir);
	$(this).one("click",esconderAlternativas);
}