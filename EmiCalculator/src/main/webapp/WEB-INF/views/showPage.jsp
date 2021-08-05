<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body style="background-color: lavender">

	<form:form action="calculateEmi" modelAttribute="product" method="post">
		<h1 align="center">Loan EMI Calculator</h1>
		<table align="center">
			<tr>
				<td><form:label path="productName">Product Name</form:label></td>
				<td><form:select path="productName" id="productName">
						<form:options items="${productList}" />
					</form:select></td>
			</tr>
			
			<tr>
				<td><form:label path="loanAmount">Loan Amount</form:label></td>
				<td><form:input path="loanAmount" id="loanAmount"/> <form:errors
						path="loanAmount" /></td>
			</tr>
			<tr>
				<td><form:label path="loanTenure">Loan Tenure (in Month)</form:label></td>
				<td><form:input path="loanTenure" id="loanTenure" /> <form:errors
						path="loanTenure" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="CalculateEMI" id="submit"></td>
			</tr>
		</table>
	</form:form>


</body>
</html>
