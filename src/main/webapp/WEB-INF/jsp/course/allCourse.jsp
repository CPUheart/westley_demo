<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/23
  Time: 11:14
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
    <title>课程列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                    <small>课程列表</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${path}/school/coures/toAddCourse">新增</a>
            <a class="btn btn-primary" href="${path}/school">返回</a>
        </div>
        <div class="col-md-4 column">
            <form>
                <input type="text" name="name"><br>
                <input type="button" value="按课程名搜索" onclick="queryByName()">
            </form>
            <script type="text/javascript">
                function queryByName() {
                    var form = document.forms[0];
                    form.action = "<%=basePath%>coures/queryByName";
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
                    <th>课程名称</th>
                    <th>课程是否关闭</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="Course" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${Course.name}</td>
                        <td>${Course.open}</td>
                        <td>
                            <a href="${path}/school/course/toUpdateCourse?id=${Course.id}">更改</a> |
                                <a href="javascript:deleteCourse('${Course.id}')">删除</a>

                                <script type="text/javascript">
                                    function deleteCourse() {
                                        var Id= arguments[0];
                                        msg='是否删除？';
                                        if(window.confirm(msg)) {
                                            URL="${path}/school/class/deleteCourseById?id="+Id;
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
