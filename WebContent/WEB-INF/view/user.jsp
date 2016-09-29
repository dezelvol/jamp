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
	
	<!-- <br><br>
	ADD MENTOR?<br>
	<form action="mentor/add" method="post">
		<label>name<input name="name" id="name" /></label><br>
		<label>pass<input name="pass" id="pass" /></label><br>
		<input type="submit" value="ok"/>
	</form>
	<br><br>
	LIST OF MENTORS
	<br><br>
	Names of mentors<br>
	<c:forEach items="${mentors}" var="user">
	    ${user.name}<form action="mentor/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
	</c:forEach>
	
	<br><br>
	ADD MENTEE?<br>
	<form action="participant/add" method="post">
		<label>name<input name="name" id="name" /></label><br>
		<label>pass<input name="pass" id="pass" /></label><br>
		<input type="submit" value="ok"/>
	</form>
	<br><br>
	LIST OF MENTEES
	<br><br>
	Names of mentees<br>
	<c:forEach items="${participants}" var="user">
	    ${user.name} mentor ${user.mentor}<form action="participant/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
	</c:forEach> -->
</BODY>
</HTML>
 