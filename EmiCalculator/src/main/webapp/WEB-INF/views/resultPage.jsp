<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<body bgcolor="lavender">
	<center>
		<h2>Loan EMI Calculator!!</h2>
		<table border="1">
			<tr><td>ProductName:</td><td>${product.productName}</td></tr>
			<tr><td>Loan Amount:</td><td>${product.loanAmount}</td></tr>
			<tr><td>Tenure:</td><td>${product.loanTenure}</td></tr>
			<tr><td>Loan EMI:</td><td>${emi}</td></tr>
		</table>
	</center>

</body>
</html>