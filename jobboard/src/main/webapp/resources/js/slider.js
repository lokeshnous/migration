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
	
	$('#slider2').cycle({
		fx: 'uncover',
		timeout: 0,
		prev: '#slider2PrevBtn',
		next: '#slider2NextBtn',
		speed: 'fast',
		pagerAnchorBuilder: pagerFactory
	});
	function pagerFactory(idx, slide) {
		var s = idx > 2 ? ' style="display:none"' : '';
		return '<li'+s+'><a href="#">'+(idx+1)+'</a></li>';
	};
	
	$('#slider3').cycle({
		fx: 'uncover',
		timeout: 0,
		prev: '#slider3PrevBtn',
		next: '#slider3NextBtn',
		speed: 'fast',
		pagerAnchorBuilder: pagerFactory
	});
	function pagerFactory(idx, slide) {
		var s = idx > 2 ? ' style="display:none"' : '';
		return '<li'+s+'><a href="#">'+(idx+1)+'</a></li>';
	};
}); 

$(document).ready(function(){
	
    $("#slider4PrevBtn").click(function() {
		$.ajax({
			url : '../healthcarejobs/featuredEmplist.html?moveBy=prev',
			data : ({}),						
			success : function(data) {
				$.ajax({
					url : '../healthcarejobs/homeFeaturedEmps.html',
					data : ({}),
					
					success : function(data) {
					$("#slider1FramesId").html(data);
					},
					error : function(data) {
					},
					complete : function(data) {
						// do nothing for now.
					}
				});
			},
			error : function(data) {
			},
			complete : function(data) {
				// do nothing for now.
			}
	});
    });
    $("#slider4NextBtn").click(function() {
		$.ajax({
			url : '../healthcarejobs/featuredEmplist.html?moveBy=next',
			data : ({}),						
			success : function(data) {
				$.ajax({
					url : '../healthcarejobs/homeFeaturedEmps.html',
					data : ({}),
					
					success : function(data) {
					$("#slider1FramesId").html(data);
					},
					error : function(data) {
					},
					complete : function(data) {
						// do nothing for now.
					}
				});
			},
			error : function(data) {
			},
			complete : function(data) {
				// do nothing for now.
			}
	});
    });
  });

