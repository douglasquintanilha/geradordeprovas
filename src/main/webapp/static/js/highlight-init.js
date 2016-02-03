$(document).ready(function() {
  $('code').each(function(i, block) {
	  console.log(block);
    hljs.highlightBlock(block);
  });
});
