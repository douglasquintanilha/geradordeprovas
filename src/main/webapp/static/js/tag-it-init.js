$.getJSON('/GeradorDeProvas/json',inicializaTags);

function inicializaTags(data){
	$("#myTags").tagit({
    	allowSpaces: true,
    	availableTags: data,
    	singleField: true
    });
}
