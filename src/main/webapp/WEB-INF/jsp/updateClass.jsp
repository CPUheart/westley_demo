<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/19
  Time: 20:01
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
    <title>更新班级信息</title>
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
                    <small>修改班级信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
            <input type="hidden" name="id" value="${requestScope.get('class').id}"><br><br><br>
        年级<input type="text" name="grade" value="${requestScope.get('class').grade}"><br><br><br>
        班级<input type="text" name="classNumber" value="${requestScope.get('class').classNumber}"><br><br><br>
        <input type="button" value="修改" onclick="updateClass()">
    </form>
    <script type="text/javascript">
        function updateClass() {
            var form = document.forms[0];
            form.action = "<%=basePath %>class/updateClass";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
