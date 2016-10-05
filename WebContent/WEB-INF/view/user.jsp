<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
</HEAD>
<BODY>
	LIST OF ALL USERS
	<br>
	<c:forEach items="${users}" var="user">
	    id: ${user.id}, name: ${user.name}, created: ${user.created}, created by(id): ${user.createdBy.id}, last updated: ${user.lastUpdated}, last updated by(id): ${user.lastUpdatedBy.id}
	    <form style="display:inline;" action="user/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
	</c:forEach>
	<table>
	<tr>
	<td>
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
		    ${user.id} ${user.name}<form action="mentor/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
		</c:forEach>
	</td>
	<td>
		ADD MENTEE?<br>
		<form action="participant/add" method="post">
			<label>name<input name="name" id="name" /></label><br>
			<label>pass<input name="pass" id="pass" /></label><br>
			<label>mentor ID<input name="mentor" id="mentor" /></label><br>
			<input type="submit" value="ok"/>
		</form>
		<br><br>
		LIST OF MENTEES
		<br><br>
		Names of mentees<br>
		<c:forEach items="${participants}" var="user">
		    ${user.id} ${user.name} mentor: ${user.mentor.name}<form action="participant/delete" method="post"><input name="id" type="hidden" value="${user.id}"/><input type="submit" value="delete"/></form><br>
		</c:forEach>
	</td>
	</tr>
	</table>
</BODY>
</HTML>
 