$(function() {
	$('#slider1').cycle({
		fx: 'uncover',
		timeout: 0,
		prev: '#slider1PrevBtn',
		next: '#slider1NextBtn',
		speed: 'fast',
		pagerAnchorBuilder: pagerFactory
	});
	function pagerFactory(idx, slide) {
		var s = idx > 2 ? ' style="display:none"' : '';
		return '<li'+s+'><a href="#">'+(idx+1)+'</a></li>';
	};
}); 