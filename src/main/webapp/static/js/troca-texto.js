var botaoExibirAlternativas = $(".botao-exibir-alternativas");
var botaoEsconderAlternativas = $(".botao-esconder-alternativas");
botaoExibirAlternativas.click(function() {
		 botaoEsconderAlternativas.show();
		 botaoExibirAlternativas.hide();
});

botaoEsconderAlternativas.click(function() {
	 botaoExibirAlternativas.show();
	 botaoEsconderAlternativas.hide();
});