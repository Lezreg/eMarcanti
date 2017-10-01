// CL E MOIS AREA
    $('#advanced').flagStrap({
        buttonSize: "btn-lg",
        buttonType: "btn-primary",
        labelMargin: "20px",
        scrollable: false,
        scrollableHeight: "350px"
    });





$(function() {
	var owl = $('.owl-carousel').owlCarousel({
		loop	:true,
		margin	:10,
		nav		:true,
		responsive:{
			0:{
				items:1
			},
			600:{
				items:2
			},

			1024:{
				items:3
			},

			1200:{
				items:4
			}


		}
	})

	/* animate filter */
	var owlAnimateFilter = function(even) {
		$(this)
		.addClass('__loading')
		.delay(70 * $(this).parent().index())
		.queue(function() {
			$(this).dequeue().removeClass('__loading')
		})
	}

	$('.btn-filter-wrap').on('click', '.btn-filter', function(e) {
		var filter_data = $(this).data('filter');

		/* return if current */
		if($(this).hasClass('btn-active')) return;
		
		

		/* active current */
		$(this).addClass('btn-active').siblings().removeClass('btn-active');
		
		/* Filter */
		owl.owlFilter(filter_data, function(_owl) { 
			$(_owl).find('.item').each(owlAnimateFilter); 
		});
	})
})





