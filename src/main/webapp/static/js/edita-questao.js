var divProvas = $('#provas');
var divQuestoes = $('#questoes');

divProvas.hide();
divQuestoes.hide();

function mostraProvas(){
	if(divProvas.is(':visible'))
		divProvas.hide();
	else{
		divQuestoes.hide();
		divProvas.show();
	}
}

function mostraQuestoes(){
	if(divQuestoes.is(':visible'))
		divQuestoes.hide();
	else{
		divProvas.hide();
		divQuestoes.show();
	}
}