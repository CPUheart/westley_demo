<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/22
  Time: 14:00
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
        <c:if test="${ErrorCode==1}">${grade}级${classNumber}班不存在</c:if>
        <c:if test="${ErrorCode==2}">
            学号${requestScope.get("id")}已存在<br>
            该学生信息为：<br>
            学号：${requestScope.get("id")}<br>
            姓名：${requestScope.get("name")}<br>
            性别：${requestScope.get("gender")}<br>
            年级：${requestScope.get("grade")}<br>
            班级：${requestScope.get("classNumber")}<br>
        </c:if>

    </h2>
</div>
<div>
    <h2>
        <a href="${path}/school/student/allStudent">返回</a>
    </h2>
</div>
</body>
</html>
