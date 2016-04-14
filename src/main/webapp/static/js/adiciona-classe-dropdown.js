function dropdownOpen(){
	var div = $("#dropMarkdown");
	if(div.hasClass("open"))
		div.removeClass("open");
	else
		div.addClass("open");
}