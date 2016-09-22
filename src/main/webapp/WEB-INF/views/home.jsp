<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<script src="resources/js/jquery-1.9.0.min.js" type="text/javascript"></script>
</head>
<body>
<form  id="search-form">
	<h1>Demo</h1>
	 Name: <input type="text" name="name"  id="name" > <button type="submit" >Search</button> <br/>
	 <br/>
<table border="1">
	<tr>
		
		<th>Name</th>
		<th>time</th>
		<th>lat</th>
		<th>lon</th>
	</tr>
	<c:forEach items="${listLog}" var="log" >
		<tr>
			
			<td>${log.name}</td>
			<td>${log.date}</td>
			<td>${log.lat}</td>
			<td>${log.lot}</td>
		</tr>
	</c:forEach>
</table>	
</form>
</body>
 <script type="text/javascript">
        function doAjaxPost() {
        // get the form values
        var name = $('#name').val();
        alert(name);

        $.ajax({
        type: "POST",
        url: "http://localhost:8082/SpringMVCSecurity/home",
        data: "name=" + name ,
        success: function(response){
      
        },
        error: function(e){
        alert('Error: ' + e);
        }
        });
        }
        </script>
</html>
