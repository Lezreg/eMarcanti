
// CATALOUGE PAGE CAT TABS AREA


$(document).ready(function() {
	
		
	 // Setup - add a text input to each footer cell
    $('#commandesid tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search... '+title+'" />' );
    } );
	
	// DataTable

	var table = $('#commandesid').DataTable({
		dom: 'lBfrtip',
		/*buttons: [
			 { extend: 'excel', text: 'Export to Excel' }
		],*/
		
		 responsive: true,
		paging:false,
		lengthChange:false,
		searching:true,
		info:false,
		language: {
			search: "_INPUT_",
			searchPlaceholder: "Search...",
			"zeroRecords": "Aucun résultat trouvé"
		}
		
		
		
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