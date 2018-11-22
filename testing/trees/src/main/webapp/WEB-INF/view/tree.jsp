<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="js/jstree.js"></script>
<link rel="stylesheet" href="css/style.min.css" />
</head>
<body>
<div id="data" class="demo"></div>
</body>

<script>
	
	$(document).ready( function() {
	// html demo
	$('#html').jstree();

	// inline data demo
	$('#data').jstree({
		'core' : {
			"check_callback" : true,
			'data' :${accounttree}

		},
		"plugins" : [ "dnd" ]
	});
	$(document).bind("dnd_start.vakata", function(e, data) {
	    console.log("Start dnd");
	})
	.bind("dnd_move.vakata", function(e, data) {
	    console.log("Move dnd");
	})
	.bind("dnd_stop.vakata", function(e,element, data) {
	    console.log("Stop dnd");
	    console.log("the event was ==>" + e);
	    console.log("element being dropped ==>" + element);
	    console.log("the data  was ==>" + data);
	    
	});
	/* $("#data").jstree({
	    // tree...
	}).bind("move_node.jstree", function(e, data) {
	   console.log("Drop node " + data.node.id + " to " + data.parent);
	}); */
	});
</script>

</html>