<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/26
  Time: 20:52
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
        <c:if test="${ErrorCode==0}">${grade}级${classNumber}班不存在</c:if>
        <c:if test="${ErrorCode==1}">请选择年级</c:if>
        <c:if test="${ErrorCode==2}">请选择班级</c:if>
        <c:if test="${ErrorCode==3}">请选择课程</c:if>
    </h2>
</div>
<div>
    <h2>
        <input type="button" value="返回" onclick="history.go(-1);">
    </h2>
</div>
</body>
</html>