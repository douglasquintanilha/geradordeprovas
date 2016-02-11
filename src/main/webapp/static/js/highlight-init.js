$(document).ready(function() {
  $("pre code").each(function(i, block) {
	  console.log(block);
    hljs.highlightBlock(block);
  });
});
