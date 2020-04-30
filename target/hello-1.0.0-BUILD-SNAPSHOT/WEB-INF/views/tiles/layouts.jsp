<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<tiles:insertAttribute name="css" />
</head>

<body>
	<tiles:insertAttribute name="header" />
	<main class="container-fluid">
		<tiles:insertAttribute name="content" />		
	</main>

	<tiles:insertAttribute name="footer" />
</body>
<tiles:insertAttribute name="js" />

</html>
