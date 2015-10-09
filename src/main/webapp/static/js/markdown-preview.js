function Editor(input, preview) {
    this.update = function () {
      preview.html(markdown.toHTML(input.val()));
    };
    input.editor = this;
    this.update();
  }
var editor = new Editor($("#titulo"), $('#titulo-markdown-preview'));

var editorA = new Editor($("#alternativaA"), $('#alternativaA-markdown-preview'));
var editorB = new Editor($("#alternativaB"), $('#alternativaB-markdown-preview'));
var editorC = new Editor($("#alternativaC"), $('#alternativaC-markdown-preview'));
var editorD = new Editor($("#alternativaD"), $('#alternativaD-markdown-preview'));
var editorE = new Editor($("#alternativaE"), $('#alternativaE-markdown-preview'));

//$('#titulo,[id=^alternativa]').each( new Editor( this, $());


$("#titulo").on('input',function(){
	editor.update();
});

$("#alternativaA").on('input',function(){
	editorA.update();
});

$("#alternativaB").on('input',function(){
	editorB.update();
});

$("#alternativaC").on('input',function(){
	editorC.update();
});

$("#alternativaD").on('input',function(){
	editorD.update();
});

$("#alternativaE").on('input',function(){
	editorE.update();
});