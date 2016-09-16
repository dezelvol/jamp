<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
</HEAD>
<BODY>
	User Added
	<br><br>
	Names of users<br>
	<c:forEach items="${users}" var="user">
	    ${user.name}<br>
	</c:forEach>
</BODY>
</HTML>
 