<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/23
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
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
                    <small>学生列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${path}/school/student/toAddStudent">新增</a>
        </div>
        <div class="col-md-4 column"><form>
            <input type="text" name="grade">
            <input type="text" name="classNumber">
            <input type="button" value="按班级搜索" onclick="queryByClass()">
        </form>
            <script type="text/javascript">
                function queryByClass() {
                    var form = document.forms[0];
                    form.action = "<%=basePath %>student/queryByClass";
                    form.method = "post";
                    form.submit();
                }
            </script>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="Student" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${Student.id}</td>
                        <td>${Student.name}</td>
                        <td>${Student.gender}</td>
                        <td>
                            <a href="${path}/school/student/toUpdateStudent?id=${Student.id}">更改</a> |
                            <a href="${path}/school/student/deleteStudent/${Student.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
