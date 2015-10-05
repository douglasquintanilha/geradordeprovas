$("#titulo").on('focus',function(){
	$(this).prev(".esconde").show();
});

$("#titulo").on('blur',function(){
	$(this).prev(".esconde").hide();
});


$(".alternativa").on('focus',function(){
	$(this).parent().prev(".esconde").show();
});

$(".alternativa").on('blur',function(){
	$(this).parent().prev(".esconde").hide();
});


