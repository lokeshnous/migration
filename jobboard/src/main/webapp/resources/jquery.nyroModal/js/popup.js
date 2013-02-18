jQuery.fn.displaypopup = function(detailClass, width, height) {			
	$(detailClass).nm({showCloseButton: false});
	$(detailClass).nm({closeOnClick:  false});
    $(detailClass).nyroModal({
      sizes: {
        initW: width, initH: height,
        minW: width, minH: height,
        w: width, h: height
      },
      closeOnEscape: false,
      keyHandle: function(event) {
    	  if (event.keyCode == 13){
    		  return false;
    	  }
      },
      callbacks: {   	
        beforeShowCont: function() { 
            width = $('.nyroModalCont').width();
            height = $('.nyroModalCont').height();
            $('.nyroModalCont iframe').css('width', width);
            $('.nyroModalCont iframe').css('height', height);
        }
      }      
    });	
	};	
