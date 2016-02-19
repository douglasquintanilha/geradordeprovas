function getTimeRemaining(endtime){
  var t = Date.parse(endtime) - Date.now();
  var seconds = Math.floor( (t/1000) % 60 );
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  
  var remainingTime = {
		    'total': t,
		    'days': days,
		    'hours': hours,
		    'minutes': minutes,
		    'seconds': seconds
		  };
  return beatifyDate(remainingTime);
}

function beatifyDate(date){

	if(date.hours < 10 ){
		date.hours = ("0" + date.hours)	;
	}
	if(date.minutes< 10 ){
		date.minutes = ("0" + date.minutes)
	}
	if(date.seconds < 10 ){
		date.seconds = ("0" + date.seconds)
	}
	return date;
}

function initializeClock(id, endtime){
  var clock = document.getElementById(id);
  var timeinterval = setInterval(function(){
    var t = getTimeRemaining(endtime);
    if(t.total<=1){
    	clearInterval(timeinterval);
    	$("#command").submit();	
    }else{
	    clock.innerHTML =
	    	t.hours +':' 
	    	+ t.minutes + ':' 
	    	+ t.seconds;
    }
  },1000);
}

Date.prototype.addMinutes = function(minutes) {
  this.setMinutes(this.getMinutes() + minutes);
  return this;
};


var deadline = new Date();
var duracao = document.getElementById('duracao').value;

deadline.setMinutes(deadline.getMinutes() + parseInt(duracao, 10));
initializeClock('clock-div', deadline);