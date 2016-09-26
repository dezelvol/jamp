<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
</HEAD>
<BODY>
	ADD USER?<br>
	<form action="user/add" method="post">
		<label>name<input name="name" id="name" /></label><br>
		<label>pass<input name="pass" id="pass" /></label><br>
		<input type="submit" value="ok"/>
	</form>
	<br><br>
	LIST OF USERS
	<br><br>
	Names of users<br>
	<c:forEach items="${users}" var="user">
	    ${user.name}<form action="user/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
	</c:forEach>
</BODY>
</HTML>
 