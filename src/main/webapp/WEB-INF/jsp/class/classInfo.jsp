<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/23
  Time: 19:17
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
    <title>班级列表</title>
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

    <div class="row">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>班级详情</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            ${requestScope.get('class').grade}级${requestScope.get('class').classNumber}班
        </div>
        <div class="col-md-4 column">
            班主任：${requestScope.get('teacher').name}
        </div>
        <div class="col-md-4 column">
            班级人数：${requestScope.get('studentAmount')}
        </div>
    </div>
    <div class="row">
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
                <c:forEach var="Student" items="${requestScope.get('students')}" varStatus="status">
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
