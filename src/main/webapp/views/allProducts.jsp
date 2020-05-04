<%-- 
    Document   : allProducts
    Created on : May 4, 2020, 10:18:00 AM
    Author     : brend
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Products</title>
    <script> 
        function Areyousure()
         {
             
  var txt;
  var Confirm = confirm("Are you sure you want to delete this? " );
  if (Confirm === null || Confirm === "") {
    txt = "User cancelled the prompt.";
    window.location.href = "/Assignment2PartD/product/viewall";
  } else {
		window.location.href = "/Assignment2PartD/product/Delete?code=${product.code}";
	}
  document.getElementById("demo").innerHTML = txt;

             
             
         }
        </script>
    </head> 
   <body>
       <security:authorize access="!isAuthenticated()">
       <p>Hi Guest! Please <a href="/Assignment2PartD/login">Login</a></p>
       </security:authorize>
       <security:authorize access="hasRole('Admin')">
            <p>Welcome ${uname}, <a href="/Assignment2PartD/logout">logout</a></p>
             </security:authorize>
        <security:authorize access="hasRole('SuperAdmin')">
          <p>Welcome ${uname}, <a href="/Assignment2PartD/logout">logout</a></p>
        </security:authorize>
               <security:authorize access="hasRole('User')">
           
             <p>Welcome ${uname}, <a href="/Assignment2PartD/logout">logout</a></p>
        </security:authorize>
        <table style="width:100%">
            <tr>
            <th align="left">Code</th>
             <th align="left">Name</th>
             <th align="left">Description</th>
             <th align="left">Buy Price</th>
             <th align="left">Sell Price</th>
             <th align="left">Qty In Stock</th>
             <security:authorize access="hasRole('Admin')">
             <th align="left">Actions</th>
             </security:authorize>
              <security:authorize access="hasRole('SuperAdmin')">
             <th align="left">Actions</th>
             </security:authorize>
            </tr>
            <c:forEach items="${productList}" var="product"> 
                <tr>
                    <td>${product.code}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td><fmt:formatNumber type="currency" maxFractionDigits="2" currencySymbol="€" value="${product.buyPrice}" /></td>
                    <td><fmt:formatNumber type="currency" maxFractionDigits="2" currencySymbol="€" value="${product.sellPrice}" /></td>
                    <td>${product.quantityInStock}</td>
                     <security:authorize access="hasRole('Admin')">
                    <td><a href="/Assignment2PartD/product/Delete?code=${product.code}" onclick="Areyousure()">Delete</a></td>
                     </security:authorize>
                     <security:authorize access="hasRole('SuperAdmin')">
                    <td><a href="/Assignment2PartD/product/addProduct">Add Product</a></td>
                    <td><a href="/Assignment2PartD/product/Delete?code=${product.code}" onclick="Areyousure()">Delete</a></td>
                     </security:authorize>
                </tr>
            </c:forEach>
        </table>
             
    </body>
</html>

