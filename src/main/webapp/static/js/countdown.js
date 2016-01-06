function getTimeRemaining(endtime){
  var t = Date.parse(endtime) - Date.now();
  var seconds = Math.floor( (t/1000) % 60 );
  var minutes = Math.floor( (t/1000/60) % 60 );
  var hours = Math.floor( (t/(1000*60*60)) % 24 );
  var days = Math.floor( t/(1000*60*60*24) );
  return {
    'total': t,
    'days': days,
    'hours': hours,
    'minutes': minutes,
    'seconds': seconds
  };
}

function initializeClock(id, endtime){
  var clock = document.getElementById(id);
  var timeinterval = setInterval(function(){
    var t = getTimeRemaining(endtime);
    clock.innerHTML =
    t.hours +
    ':' + t.minutes + 
    ':' + t.seconds;
    if(t.total<=0){
      $("#	command").submit();	
      clearInterval(timeinterval);
    }
  },1000);
}

Date.prototype.addMinutes = function(minutes) {
  this.setMinutes(this.getMinutes() + minutes);
  return this;
};
function formatToClock(minutes){
  
}


var deadline = new Date();
console.log(deadline);
var duracao = document.getElementById('duracao').value;

deadline.setMinutes(deadline.getMinutes() + parseInt(duracao, 10));
console.log(deadline);
initializeClock('clock-div', deadline);