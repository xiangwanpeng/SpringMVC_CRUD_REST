<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/emp"
		method="POST" modelAttribute="employee">
		<c:if test="${employee.id == null }">
		LastName:<form:input path="lastName" />
		&nbsp;<form:errors path="lastName"></form:errors>
			<br>
			<br>
		</c:if>
		<c:if test="${employee.id != null }">
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT">
			<%-- 对于 _method 不能使用 form:hidden 标签, 因为 modelAttribute 对应的 bean 中没有 _method 这个属性 --%>
			<%-- 
			<form:hidden path="_method" value="PUT"/>
			--%>
		</c:if>
		
		Email:<form:input path="email" />
		&nbsp;<form:errors path="email"></form:errors>
		<br>
		<br>
		<%
			Map<String, String> genders = new HashMap<String, String>();
				genders.put("0", "Female");
				genders.put("1", "Male");
				request.setAttribute("genders", genders);
		%>
		Gender:<form:radiobuttons path="gender" items="${genders }" />
		<br>
		<br>
		Birth:<form:input path="birth" />
		&nbsp;<form:errors path="birth"></form:errors>
		<br>
		<br>
		Department:<form:select path="department.id" items="${departments }"
			itemLabel="departmentName" itemValue="id"></form:select>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>