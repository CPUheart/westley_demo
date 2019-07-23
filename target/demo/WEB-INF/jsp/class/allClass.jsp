<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/19
  Time: 13:45
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
                    <small>班级列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${path}/school/class/toAddClass">新增</a>
        </div>

        <div class="col-md-4 column"><form>
            <input type="text" name="name">
            <input type="button" value="按班主任姓名搜索" onclick="queryByAdvisor()">
            <input type="button" value="按教师姓名搜索" onclick="queryByTeacherName()">
        </form>
            <script type="text/javascript">
                function queryByAdvisor() {
                    var form = document.forms[0];
                    form.action = "<%=basePath %>class/queryByAdvisor";
                    form.method = "post";
                    form.submit();
                }
            </script>
            <script type="text/javascript">
                function queryByTeacherName() {
                    var form = document.forms[0];
                    form.action = "<%=basePath %>class/queryByTeacherName";
                    form.method = "post";
                    form.submit();
                }
            </script>
        </div>
        <div class="col-md-4 column">
            <form>
                <input type="number" name="grade"  oninput="if(value.length>4)value=value.slice(0,4)" >
                <input type="button" value="按年级搜索" onclick="queryByGrade()">
            </form>
            <script type="text/javascript">
                function queryByGrade() {
                    var form = document.forms[1];
                    form.action = "<%=basePath %>class/queryByGrade";
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
                    <th>年级</th>
                    <th>班级</th>
                    <%--<th>班级详情</th>--%>
                    <th>班主任</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ClassTeacher" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${ClassTeacher.grade}</td>
                        <td>${ClassTeacher.classNumber}</td>
                        <td>${ClassTeacher.name}</td>
                        <td>
                            <%--<a href="${path}/school/class/toUpdateClass?id=${Class.id}">更改</a> |--%>
                            <a href="${path}/school/class/deleteClassById/${ClassTeacher.classId}">删除
                            </a>
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