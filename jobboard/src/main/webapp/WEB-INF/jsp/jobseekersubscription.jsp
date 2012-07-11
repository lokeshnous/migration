<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">
function go(url)
{
window.location = url;
}

function saveSubscription(url)
{
	var isOK = confirm("Are you sure to Submit?");
if(isOK)
{
go(url);
}
}
</script>
<title>Insert title here</title>
</head>
<body>
<h3>User Settings</h3>

<table>
       
       
        <!-- <tr>
            <td><h4>Subscriptions</h4></td>
 </tr> <tr>
                <td><input type="checkbox" name="community" value="Hibernate"/>Newsletters</br>
                <input type="checkbox" name="community" value="test"/>Emailer</br>
               
                </td>
       
        <tr>
        
        <tr>
            <td><h4>Magazines</h4></td>
 </tr> <tr>
                <td><input type="checkbox" name="community" value="Hibernate"/>	Magazine 1</br>
                <input type="checkbox" name="community" value="test"/>	Magazine 2</br>
                <input type="checkbox" name="community" value="test1"/>	Magazine 3</br>
                 <input type="checkbox" name="community" value="test1"/>	Magazine 4</br>
                  <input type="checkbox" name="community" value="test1"/>	Magazine 5</br>
                   <input type="checkbox" name="community" value="test1"/>	Magazine 6</br>
                </td>
       
        <tr>
        
        <tr> -->
            <td><h4>Job Alerts</h4></td>
 </tr> <tr>
                <td><input type="checkbox" name="community" value="Hibernate"/>Mobile</br>
                <input type="checkbox" name="community" value="test"/>e-Mail</br>
                <!-- <input type="checkbox" name="community" value="test1"/>Facebook</br>
                <td><input type="checkbox" name="community" value="Hibernate"/>LinkedIn</br>
                <input type="checkbox" name="community" value="test"/>Twitter</br> -->
                </td>
     
        
       
    </table>
 <input value="submit"
			onclick="javascript:saveSubscription('saveJobSeekerSubscription.html?id=2');"
			type="button">
</body>
</html>