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
            <a class="btn btn-primary" href="javascript:history.back(-1)">返回</a>
        </div>
        <div class="col-md-4 column">
          <%--  <form>
                <input type="text" name="grade" value="${requestScope.get('grade')}">
                <input type="text" name="classNumber" value="${requestScope.get('classNumber')}">
                <input type="button" value="按班级搜索" onclick="queryByClass()">
            </form>
            <script type="text/javascript">
                function queryByClass() {
                    var form = document.forms[0];
                    form.action = "<%=basePath %>student/queryByClass";
                    form.method = "post";
                    form.submit();
                }
            </script>--%>
        </div>
        <div class="col-md-4 column">
            <form action="<%=basePath%>student/queryByName" method="post">
                <input type="text" name="name" placeholder="按姓名搜索">
                <input type="submit">
            </form>
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
                            <a href="${path}/school/class/classInfo?id=${StudentClass.classId}">班级详情</a> |
                            <a href="${path}/school/student/toUpdateStudent?id=${StudentClass.id}">更改</a> |
                            <%--<a href="${path}/school/student/deleteStudent/${StudentClass.id}">删除</a>--%>
                            <a href="javascript:deleteStudent('${StudentClass.id}')">删除</a>
                            <script type="text/javascript">
                                function deleteStudent() {
                                    var Id= arguments[0];
                                    msg='是否删除？';
                                    if(window.confirm(msg)) {
                                        URL="${path}/school/student/deleteStudent/"+Id;
                                        window.location=URL;
                                    }
                                }
                            </script>
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
