<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/24
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>添加教师</title>
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
                    <small>新增教师</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
        编号<input  onkeyup="value=value.replace(/[\W]/g,'')" type="text" name="id" oninput="if(value.length>9)value=value.slice(0,9)" ><br><br><br>
        姓名<input type="text" name="name" maxlength="15"><br><br><br>
        <%--性别<input type="text" name="gender"><br><br><br>--%>
        性别<input type="radio" name="gender" value="男" checked="checked">男 <input type="radio" name="gender" value="女">女<br><br><br>
        <input type="button" value="添加" onclick="addTeacher()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
        function addTeacher() {
            var form = document.forms[0];
            form.action = "<%=basePath %>teacher/addTeacher";
            form.method = "post";
            form.submit();
        }
    </script>
</div>

</body>
</html>

