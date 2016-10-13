<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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
		<f:form action="mentor/add" method="post" modelAttribute="mentor">
			<label>name<f:input path="name" name="name" id="name" /></label><br>
			<f:errors path="name"/><br/>
			<label>pass<f:input path="password" name="password" id="password" /></label><br>
			<f:errors path="password"/><br/>
			<input type="submit" value="ok"/>
		</f:form>
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
		<f:form action="participant/add" method="post" modelAttribute="participant">
			<label>name<f:input path="name" name="name" id="name" /></label><br>
			<f:errors path="name"/><br/>
			<label>pass<f:input path="password" name="password" id="password" /></label><br>
			<f:errors path="password"/><br/>
			<label>mentor ID<f:input path="mentor" name="mentor" id="mentor" /></label><br>
			<f:errors path="mentor"/><br/>
			<input type="submit" value="ok"/>
		</f:form>
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
 