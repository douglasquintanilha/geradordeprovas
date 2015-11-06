converter = new showdown.Converter();

function Editor(input, preview) {
	this.update = function() {
		var html = converter.makeHtml(input.val());
		preview.html(html);
		if(html.indexOf("<pre><code>") > -1){
			hljs.highlightBlock(preview.find("code")[0]);
		}
	};
}

$(".alternativa,.grupo-titulo").each(function() {
	var $this = $(this);
	var $entrada = $this.find(".entrada");	
	var $preview = $this.find(".preview-markdown");
	var $esconde = 	$this.find(".esconde");

	var editorAlternativa = new Editor($entrada, $preview);
	$entrada.on('input', function() {
		editorAlternativa.update();
	});
	$entrada.on("focus",function(){
		$esconde.show();
	});
	$entrada.on("blur",function(){
		//$esconde.hide();
	});
});