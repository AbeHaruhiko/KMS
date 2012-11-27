<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
</head>
<body>
<p></p>
<form method="post" action="addMember">
<input type="text" name="gplusId" placeholder="Enter your G+ ID"/><br />
<input type="submit" value="入里！"/>
</form>

<c:forEach var="member" items="${memberList}">
${f:h(member.gplusId)}
<hr />
</c:forEach>
</body>
</html>
