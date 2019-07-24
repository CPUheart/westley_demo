<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/24
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>更改教师信息</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <a href="${path}/school/">学校信息管理系统</a>
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>更改教师信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
        编号<input type="text" readonly="true" name="id" oninput="if(value.length>9)value=value.slice(0,9)"  value="${requestScope.get('teacher').id}"><br><br><br>
        姓名<input type="text" name="name" maxlength="30" value="${requestScope.get('teacher').name}"><br><br><br>
        <%--性别<input type="radio" name="gender" value="男" checked="checked">男 <input type="radio" name="gender" value="女">女<br><br><br>--%>
        <%--性别<input type="text" name="gender" value="${requestScope.get('teacher').gender}"><br><br><br>--%>
        性别
        <input type="radio" name="gender" value="男" <c:if test="${requestScope.get('teacher').gender=='男'}">checked="checked"</c:if>>男
        <input type="radio" name="gender" value="女" <c:if test="${requestScope.get('teacher').gender=='女'}">checked="checked"</c:if>>女
        <br><br><br>
        <input type="button" value="修改" onclick="updateTeacher()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
        function updateTeacher() {
            var form = document.forms[0];
            form.action = "<%=basePath %>teacher/updateTeacher";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
