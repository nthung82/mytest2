<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<form action="home1" method="get" id="myForm">
	<input type="text" name="hungnt">
	 <input type="button" onclick="myFunction()" value="Submit form">
	</form>
	
<script>
function myFunction() {
    document.getElementById("myForm").submit();
}
</script>
</body>
</html>
