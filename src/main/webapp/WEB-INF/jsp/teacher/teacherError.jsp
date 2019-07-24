<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/24
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
<div>
    <h2>
        <c:if test="${ErrorCode==0}">
            编号${requestScope.get('id')}已存在<br>
            该教师信息为：<br>
            编号：${requestScope.get('id')}<br>
            姓名：${requestScope.get('name')}<br>
            性别：${requestScope.get('gender')}<br>
        </c:if>
    </h2>
</div>
<div>
    <h2>
        <%--<a href="${path}/school/class/allClass">返回</a>--%>
        <input type="button" value="返回" onclick="history.go(-1);">
    </h2>
</div>
</body>
</html>
