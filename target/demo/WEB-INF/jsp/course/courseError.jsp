<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/24
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
<div>
    <h2>
        <c:if test="${ErrorCode==0}">新增课程失败，课程“{name}”已存在</c:if>
        <c:if test="${ErrorCode==1}">更改课程失败，课程“{name}”已存在</c:if>
    </h2>
</div>
<div>
    <h2>
        <input type="button" value="返回" onclick="history.go(-1);">
    </h2>
</div>
</body>
</html>