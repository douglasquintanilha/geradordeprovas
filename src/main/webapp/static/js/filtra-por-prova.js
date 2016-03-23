(function($) {


  var domCache = $('.prova-element');

  $('#busca').on('input', function() {
  
    var digitado = $(this).val().trim();
  
    if(digitado) {
  
        domCache.hide().filter(function() {
        	if($(this).text().match(new RegExp(digitado, 'i')))
        		console.log($(this));
           return $(this).text().match(new RegExp(digitado, 'i'));
        }).show();
    
    } else { domCache.show(); }
  
  });

})(jQuery);
