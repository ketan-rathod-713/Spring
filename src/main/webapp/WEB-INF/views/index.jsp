<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page isELIgnored="false" %>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Management System</title>
<style type="text/css">
 h1{
 text-align: center;
 }
    table {
      border-collapse: collapse;
      width: 100%;
    }

    th, td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #f5f5f5;
    }
    
    .header{
    	font-weight:bold;
    }
    
    table{
    	border: 2px solid grey;
    	
    }
    .topHeader{
    	background-color: grey;
    	padding:15px;
    	color:white;
    }
    .createEventForm{
    	margin:40px 0px;
    	display:flex;
    	justify-content: center;
    	align-items: center;
    }
    .createEventForm input {
    	padding: 5px 10px;
    }

</style>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<h1 class="topHeader">Welcome To Event Management Application</h1>

<form action="create" class="createEventForm">
	<input class="btn btn-primary" type="submit" value="Create Event" >
</form>

<h1>All Events</h1>
<table>
<tr class="header">
<td>Event Id </td>
<td>Event Name </td>
<td> Event Description </td>
<td> User Id </td>
<td> DELETE </td>
<!--   <td> UPDATE </td> -->
</tr>
<c:forEach var="item" items="${events}">

<tr>
<td>${item.eventId}</td>
<td>${item.eventName}</td>
<td>${item.eventDescription}</td>
<td>${item.userId}</td>
<td><form action="deleteEvent">
	<input name="eventId" hidden value=${item.eventId} />
	<input class="btn btn-danger" type="submit" value="delete" name="delete"/>
</form></td>
<!--<td><button>UPDATE</button></td> -->
</tr>

</c:forEach>
</table>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
<script type="text/javascript">

</script>
</body>
</html>