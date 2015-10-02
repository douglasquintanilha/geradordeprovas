function Editor(input, preview) {
    this.update = function () {
      preview.innerHTML = markdown.toHTML(input.value);
    };
    input.editor = this;
    this.update();
  }
var editor = new Editor($("#titulo").get(0), $('#titulo-markdown-preview').get(0));

var editorA = new Editor($("#alternativaA").get(0), $('#alternativaA-markdown-preview').get(0));
var editorB = new Editor($("#alternativaB").get(0), $('#alternativaB-markdown-preview').get(0));
var editorC = new Editor($("#alternativaC").get(0), $('#alternativaC-markdown-preview').get(0));
var editorD = new Editor($("#alternativaD").get(0), $('#alternativaD-markdown-preview').get(0));
var editorE = new Editor($("#alternativaE").get(0), $('#alternativaE-markdown-preview').get(0));

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