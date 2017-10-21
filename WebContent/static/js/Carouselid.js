
// CATALOUGE PAGE CAT TABS AREA

//jQuery.noConflict();
$(document).ready(function() {
	
		
	 // Setup - add a text input to each footer cell
    $('#carouselid tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Filtrer '+title+'" />' );
    } );
	
	// DataTable

	var table = $('#carouselid').DataTable({
		dom: 'lBfrtip',
		/*buttons: [
			 { extend: 'excel', text: 'Export to Excel' }
		],*/
		
			responsive: true,
			paging:false,
			lengthChange: false,
			info: false,
			
		
		
		
	});

	
	
	// Apply the search
    table.columns().every( function () {
        var that = this;
 
        $( 'input', this.footer() ).on( 'keyup change', function () {
            if ( that.search() !== this.value ) {
                that
                    .search( this.value )
                    .draw();
            }
        } );
    } );
	
	
	

} );
	

// SINGLE POPUP ADD TO CART



$('[data-toggle="tooltip"]').tooltip(); 