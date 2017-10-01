
// CATALOUGE PAGE CAT TABS AREA


$(document).ready(function() {
	
		
	 // Setup - add a text input to each footer cell
    $('#admincatalougetid tfoot th').each( function () {
        var title = $(this).text();
        $(this).html( '<input type="text" placeholder="Search... '+title+'" />' );
    } );
	
	// DataTable

	var table = $('#admincatalougetid').DataTable({
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
        "iDisplayLength": 75,
		
		"columnDefs": [
			
			{ "width": "200px", "targets": 0 },
			{ "width": "400px", "targets": 1 },
			{
                "targets": [ 2 ],
				"searchable": false,
                "visible": false
            }
		  ],
		
		
	});

   new $.fn.dataTable.FixedHeader( table );

	
	
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
	

// FILTER 


$('.tab_button').on('click',function(){
    if(this.id == "all"){
      $('tbody tr').show();
    }else{
      showType(this.id);
      hideType(this.id);
    }
  })
  
  function showType(type){
    $('tbody tr[type="'+type+'"]').show();
  }
  
  function hideType(type){
    $('tbody tr').not("[type='"+type+"']").hide();
  }
	


// SINGLE POPUP ADD TO CART



 $('#admincatalougetid').on('click', '.popupone', function () {
        var name = $('td', this).eq(1).text();
        $('#DescModal').modal("show");
    });

$('#admincatalougetid').on('click', '.popuptwo', function () {
        var name = $('td', this).eq(1).text();
        $('#DescModal2').modal("show");
    });


