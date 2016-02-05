var url = $("#json-url").data("baseurl");
$.getJSON(url,inicializaTags);

function inicializaTags(data){
	$("#myTags").tagit({
    	allowSpaces: true,
    	availableTags: data,
    	singleField: true
    });
}
 