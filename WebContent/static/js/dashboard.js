
// CATALOUGE PAGE CAT TABS AREA


$(document).ready(function() {
	
	
	
    // Setup - add a text input to each footer cell
    $('#dashbaordid tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Filtrer '+title+'" />' );
    } );
	
	
	// DataTable

	var table = $('#dashbaordid').DataTable({
		dom: 'lBfrtip',
		/*buttons: [
			 { extend: 'excel', text: 'Export to Excel' }
		],*/
		
			responsive: true,
			language: {
			search: "_INPUT_",
			searchPlaceholder: "Search...",
			paginate: {
			  next: '<i class="fa fa-angle-right">',
			  previous: '<i class="fa fa-angle-left">'  
			},
			"lengthMenu": "Afficher _MENU_ Entrées",
			 "info": "Affichage _START_ à _END_ sur _TOTAL_ Entrées",
			"zeroRecords": "Aucun résultat trouvé",
			"infoFiltered": "(filtré sur _MAX_ entrées au total)",
			
		},
		
		 "aLengthMenu": [[10,25,50, 75, -1], [10,25,50, 75, "All"]],
        "iDisplayLength": 50,
		
		
		
	});


	
} );
	

// SINGLE POPUP ADD TO CART



$('[data-toggle="tooltip"]').tooltip(); 