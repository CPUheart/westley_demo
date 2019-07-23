<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/20
  Time: 13:34
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
        <div class="col-md-4 column">
            <form>
            <%--<select name="grade" id="grade" onchange="queryByGrade()">
                <option value="0">==请选择==</option>
                <c:forEach var="Grade" items="${requestScope.get('grade')}" varStatus="var">
                    <option value="${Grade}" <c:if test="${Grade==grade}">selected</c:if>> ${Grade}</option>
                </c:forEach>
            </select>
            <select name="classNumber" id = "classNumber">
                <c:forEach var="Class" items="${requestScope.get('class')}" varStatus="var">
                    <option value="${Class.classNumber}" <c:if test="${Class.classNumber==classNumber}">selected</c:if>> ${Class.classNumber}</option>
                </c:forEach>
            </select>
            <script type="text/javascript">
                function queryByGrade() {
                    var form = document.forms[0];
                    form.action = "<%=basePath %>student/queryByGrade";
                    form.method = "post";
                    form.submit();
                }
            </script>--%>
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
                    <th>年级</th>
                    <th>班级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="StudentClass" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${StudentClass.id}</td>
                        <td>${StudentClass.name}</td>
                        <td>${StudentClass.gender}</td>
                        <td>${StudentClass.grade}</td>
                        <td>${StudentClass.classNumber}</td>
                        <td>
                            <a href="${path}/school/student/toUpdateStudent?id=${StudentClass.id}">更改</a> |
                            <a href="${path}/school/student/deleteStudent/${StudentClass.id}">删除</a>
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
