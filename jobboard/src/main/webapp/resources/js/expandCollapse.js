jQuery(function(){
			$(".refineResultsSubContent").hide();
			$(".refineResultsItem").click(function(){
				$(this).next().toggle();
				$(this).toggleClass('plus minus');
			});
			
			
			$(".searchResultsSubContent").hide();
			
			$(".searchResultsJobInfo").click(function(e){
				$(this).next().toggle();
				$(this).toggleClass('closed open');
				$(this).children(".searchResultsColumn1").children(".sectionHeader").children().next().toggleClass("accord-open accord-close");
			});
			
			$(".clickableLink").click(function(e){
				$(this).parent().parent().next().toggle();
				$(this).parent().parent().toggleClass('closed open');
				$(this).parent().parent().children(".searchResultsColumn1").children(".sectionHeader").children().next().toggleClass("accord-open accord-close");
			});
			
			$("#orange-bg").click(function(){
				//alert('');
				$(this).addClass("orange-bg");
			});
			
		});
		
