$(function() {
	var hight = Number(document.getElementById("strcontent").offsetHeight);
	if(hight<280)
		$('#strcontent').css('height', '280px');
});