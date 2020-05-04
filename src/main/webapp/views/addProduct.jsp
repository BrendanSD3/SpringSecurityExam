<%-- 
    Document   : addProduct
    Created on : May 4, 2020, 10:18:28 AM
    Author     : brend
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        
        <form:form method="POST" action="/Assignment2PartD/product/add" modelAttribute="Product">
            <table>
                <tr> 
                    <td><form:label path="code">Code</form:label></td>
                    <td><form:input path="code" /></td> 
                    <td style="color:red"><form:errors path="code"/> </td>
               </tr> 
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" required='true'/></td>
                    <td style="color:red"><form:errors path="name"/> </td>
                </tr>
                <tr>
                    <td><form:label path="description">Description</form:label></td>
                    <td><form:input path="description" required='true'/></td>
                    <td style="color:red"><form:errors path="description"/> </td>
                    
                </tr>
                <tr>
                    <td><form:label path="buyPrice">Buy Price</form:label></td>
                    <td><form:input path="buyPrice" required='true'/></td>
                    <td style="color:red"> <form:errors path="buyPrice"/> </td>
                </tr>
               <tr>
                    <td><form:label path="sellPrice">Sell Price</form:label></td>
                    <td><form:input path="sellPrice" required='true'/></td>
                    <td style="color:red"> <form:errors path="sellPrice"/> </td>
                </tr>
               
                <tr>
                    
                    <td><form:label path="quantityInStock">Quantity In Stock</form:label></td>
                    <td><form:input type="quantityInStock" path="quantityInStock" /></td>
                    <td style="color:red"> <form:errors path="quantityInStock"/> </td>
                   
                </tr>
                <tr>
                    <td><input type="submit" value="Add Product" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>