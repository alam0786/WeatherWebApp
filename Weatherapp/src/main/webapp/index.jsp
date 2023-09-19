<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Information</title>
<style>
	body{
		text-align:center;
	}
</style>
</head>
<body>
<h1>Weather Information</h1>
<form action="WeatherServlet" method="get">
	<label for="city">Enter City Name:</label>
	<input type="text" id="city" name="city" required>
	<input type="submit" value="Get Weather">
</form>
<%
	String locationName=(String) request.getAttribute("locationName");
	if(locationName!=null){
%>
	<h2>Weather for <%=locationName %></h2>
	<p>Loaction:<%= locationName %></p>
	<p>Region: ${region}</p>
	<p>Country: ${country}</p>
	<p>TemperatureCelsius:${temperatureCelsius}</p>
	<p>temperatureFahrenheit:${temperatureFahrenheit }</p>
	<p>Wind Speed:${WindSpeed}</p>
	<%
	}
	%>
	
</body>
</html>