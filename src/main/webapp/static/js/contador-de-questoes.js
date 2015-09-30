$(":checkbox").on("change",function(){
	console.log("mudou");
	$("#questoes-marcadas").text($(":checkbox:checked").length);
});