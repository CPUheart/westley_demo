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
        ${grade}级${classNumber}班不存在
    </h2>
</div>
<div>
    <h2>
        <a href="${path}/school/student/allStudent">返回</a>
    </h2>
</div>
</body>
</html>
