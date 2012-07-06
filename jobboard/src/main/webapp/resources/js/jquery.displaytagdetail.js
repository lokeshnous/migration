jQuery.fn.displaytagdetail = function(button, detailClass, imgUp, imgDown, speed) {			
		$(this).find(button).click( function() {
			tr = $(this).parents("tr");				
			if (tr.next().hasClass(detailClass)) {
				tr.next().find("div").slideUp(function() {					    
					tr.next().remove();
				  });															
				$(this).find("img:first").attr("src",imgDown);	
				
			} else {
				tr.after("<tr class='" + detailClass + "'><td colspan=1000><div style='display:none' >" + 
						$(this).find("div").html()  +
						"</div></td></tr>");
				tr.next().addClass(tr.attr("class"));					
				$(this).find("img:first").attr("src",imgUp);
				tr.next().find("div").slideDown(speed);
			
			}
		});
	};	