function Editor(input, preview) {
	this.update = function() {
		preview.html(markdown.toHTML(input.val()));
		console.log(input.val());
		console.log(preview.text());
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
		$esconde.hide();
	});
});