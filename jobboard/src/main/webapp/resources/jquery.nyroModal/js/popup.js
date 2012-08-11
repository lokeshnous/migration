jQuery.fn.displaypopup = function(detailClass, width, height) {			
	$(detailClass).nm({showCloseButton: false});
	$(detailClass).nm({closeOnClick:  false});
    $(detailClass).nyroModal({
      sizes: {
        initW: width, initH: height,
        minW: width, minH: height,
        w: width, h: height
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
