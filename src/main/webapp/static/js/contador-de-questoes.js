$("#questoes-marcadas").text($(":checkbox:checked").length);
$(":checkbox").on("change",function(){
	$("#questoes-marcadas").text($(":checkbox:checked").length);
});