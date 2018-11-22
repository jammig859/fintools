<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href= "https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/> 
<script src= " https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="js/jquery.dataTables.yadcf.js"></script>
<title>Insert title here</title>
</head>
<body>



<script>
$( document ).ready(function() {
 var checkbookSumTable = $('#checbookTbl').DataTable( {
	 /* initComplete: function () {
         this.api().columns().every( function () {
             var column = this;
             var select = $('<select><option value=""></option></select>')
                 .appendTo( $(column.footer()).empty() )
                 .on( 'change', function () {
                     var val = $.fn.dataTable.util.escapeRegex(
                         $(this).val()
                     );

                     column
                         .search( val ? '^'+val+'$' : '', true, false )
                         .draw();
                 } );

             column.data().unique().sort().each( function ( d, j ) {
                 select.append( '<option value="'+d+'">'+d+'</option>' )
             } );
         } );
     }, */

      "bInfo": true,
      /* "scrollX": true, */
      "paging": true,
      "processing": true,
      "serverSide": false,
      responsive: true,
      /*the the show entry box.*/
      "bLengthChange": true,
      /* Disable initial sort */
      "aaSorting": [],
      searching: true,
      scrollCollapse: true,
		"ajax": {
			"url": "rest/api/getUserGrants",
			"contentType": "application/json",
			"dataType": "json",
			"type": "POST",
			"data": function(d) {
				 d.userId ="JMA337" //$("#useridSearchInput").val();//"SM3529";
				//d.termcode =//$("#termpInput").val();//"201815"; */
				return JSON.stringify(d);
			}
			
		},
		
		 "columns": [
			    { "data": "frrgrnlGrntCode" ,"orderable": false,  width: '65px', "defaultContent": ''},
			    { "data": "status","orderable": false, "width":"75px", "defaultContent": '' },
			    { "data": "analystName" ,"orderable": false , "width":"75px", "defaultContent": ''},
	            { "data": "grantTitle","orderable": false , "width":"75px" , "defaultContent": ''},
	            { "data": "investigatorName","orderable": false, "width":"75px" , "defaultContent": ''},
	            { "data": "frrgrnlBudget","orderable": false, width: '65px', "defaultContent": '' },
	            { "data": "frrgrnlYtd","orderable": false, "width":"75px", "defaultContent": '' },
	            { "data": "frrgrnlEncumbrance","orderable": false, "width":"75px" , "defaultContent": ''},
	            { "data": "agencyName","orderable": false, "width":"75px", "defaultContent": '' },
	            { "data": "sponsorId","orderable": false, "width":"75px" , "defaultContent": ''},
	            { "data": "frrgrnlTotal","orderable": false, "width":"75px", "defaultContent": '' },
	            
	          
	           
	        ],fixedColumns: true,
	        "fnDrawCallback": function(oSettings) {
	        	
	        }
	        	
      
 	});
 	yadcf.init(checkbookSumTable,[
		{column_number : 0},
	    {column_number : 1,  filter_type: "range_number_slider", filter_container_id: "external_filter_container"},
	    {column_number : 2, data: ["Yes", "No"], filter_default_label: "Select Yes/No"},
	    {column_number : 3, text_data_delimiter: ",", filter_type: "auto_complete"},
	    {column_number : 4, column_data_type: "html", html_data_type: "text", filter_default_label: "Select tag"}]);
});

 
 
//});
</script>


<table id="checbookTbl"
                        class="table table-striped table-bordered  nowrap"
                        style="cursor: pointer;" 
                        width="100%">
                        <thead>
                           <tr>
                              <%-- <th></th> --%>
                              <th>Grant Code</th>
                              <th>STATUS</th>
                              <th>Analyst Name</th>
                              <th>Grant Title</th>
                              <th>Investigator Name</th>
                              <th>Budget</th>
                              <th>Year To Date</th>
                              <th>Encumbrance</th>
                              <th>Agency Name</th>
                              <th>Sponsor ID</th>
                              <th>TOTAL</th>
                              
                           </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tfoot>
            <tr>
                <th>Grant Code</th>
                <th>STATUS</th>
                <th>Analyst Name</th>
                <th>Grant Title</th>
                <th>Investigator Name</th>
                <th>Budget</th>
                <th>Year To Date</th>
                <th>Encumbrance</th>
                <th>Agency Name</th>
                <th>Sponsor ID</th>
                <th>TOTAL</th>
            </tr>
        </tfoot>
                     </table>


</body>
</html>