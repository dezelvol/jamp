<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
</HEAD>
<BODY>
	User Added
	<br><br>
	Names of users<br>
	<c:forEach items="${users}" var="user">
	    ${user.name}<form action="user/delete" method="post"><input name="name" type="hidden" value="${user.name}"/><input type="submit" value="delete"/></form><br>
	</c:forEach>
</BODY>
</HTML>
 