<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/26
  Time: 15:37
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
    <title>任课详情</title>
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
                <h2>
                    <div class="row">
                        <div class="col-md-3 colunm">
                            任课详情
                        </div>
                        <div class="col-md-3 colunm" style="float:right">
                            <small><input type="button" value="返回" onclick="history.go(-1);"/></small>
                        </div>
                    </div>

                </h2>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            教师编号：${requestScope.get('teacherId')}
        </div>
        <div class="col-md-4 column">
            教师姓名：${requestScope.get('name')}
        </div>
        <div class="col-md-4 column">
            教师性别：${requestScope.get('gender')}
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 column">
            <div class="page-header">
                <h3>任课列表</h3>
            </div>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>年级</th>
                    <th>班级</th>
                    <th>教授课程</th>
                    <th>班级详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ClassCourse" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${ClassCourse.grade}</td>
                        <td>${ClassCourse.classNumber}</td>
                        <td>${ClassCourse.courseName}</td>
                        <td>
                            <a href="${path}/school/class/classInfo?id=${ClassCourse.classId}">班级详情</a>
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
