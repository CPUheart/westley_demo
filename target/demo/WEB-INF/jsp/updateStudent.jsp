<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/20
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>更改学生信息</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    学校信息管理系统
                </h1>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>更改学生信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
        学号<input type="text" readonly="true" name="id" value="${requestScope.get('studentClass').id}"><br><br><br>
        姓名<input type="text" name="name" value="${requestScope.get('studentClass').name}"><br><br><br>
        性别<input type="text" name="gender" value="${requestScope.get('studentClass').gender}"><br><br><br>
        年级<input type="text" name="grade" value="${requestScope.get('studentClass').grade}"><br><br><br>
        班级<input type="text" name="classNumber" value="${requestScope.get('studentClass').classNumber}"><br><br><br>
        <input type="button" value="修改" onclick="updateStudent()">
    </form>
    <script type="text/javascript">
        function updateStudent() {
            var form = document.forms[0];
            form.action = "<%=basePath %>student/updateStudent";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
