$('#busca').on('input keyup click', function() {

	if (this.value.length > 0) {
		  $('.prova-element').hide().filter(function () {
			  console.log(retiraEspacos($(this).text()));
			  return retiraEspacos($(this).text).toLowerCase().indexOf($('#busca').val().toLowerCase()) != -1; 
		  }).show();
	}
	else{
		$('.prova-element').show();
	}
});

function retiraEspacos(palavra){
	var str = "";
	for(var i=0; i < palavra.length; i++){
		if(palavra[i].match(new RegExp("[A-z]"))){
			str = str + palavra[i];
		}
	}
	return str;
}

//
//(function($) {
//
//
//  var domCache = $('.prova-element');
//
//  $('#busca').on('input', function() {
//  
//    var digitado = $(this).val().trim();
//  
//    if(digitado) {
//  
//        domCache.hide().filter(function() {
//        	if($(this).text().match(new RegExp(digitado, 'i')))
//           return $(this).text().match(new RegExp(digitado, 'i'));
//        }).show();
//    
//    } else { domCache.show(); }
//  
//  });
//
//})(jQuery);
