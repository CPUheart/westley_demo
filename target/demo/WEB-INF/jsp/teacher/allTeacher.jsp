
<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/20
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>老师列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <a href="http://localhost:8081/school/">学校信息管理系统</a>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>教师列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${path}/school/teacher/toAddTeacher">新增</a>
            <a class="btn btn-primary" href="${path}/school">返回</a>
        </div>
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>教师编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>是否担任班主任</th>
                    <th>年级</th>
                    <th>班级</th>
                    <th>班级详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="TeacherAdvisor" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${TeacherAdvisor.id}</td>
                        <td>${TeacherAdvisor.name}</td>
                        <td>${TeacherAdvisor.gender}</td>

                        <c:if test="${TeacherAdvisor.advisor==1}">
                            <td>是</td>
                            <td>${TeacherAdvisor.grade}</td>
                            <td>${TeacherAdvisor.classNumber}</td>
                            <td>
                                <a href="${path}/school/class/classInfo?id=${TeacherAdvisor.classId}">班级详情</a>
                            </td>
                            <td>
                                <a href="${path}/school/teacher/toUpdateTeacher?id=${TeacherAdvisor.id}">修改</a>
                            </td>
                        </c:if>
                        <c:if test="${TeacherAdvisor.advisor==0}">
                            <td>否</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <a href="${path}/school/teacher/toUpdateTeacher?id=${TeacherAdvisor.id}">修改</a> |
                                <%--<a href="${path}/school/teacher/deleteTeacher/${TeacherAdvisor.id}">删除</a>--%>
                                <a href="javascript:deleteTeacher('${TeacherAdvisor.id}')">删除</a>
                                <script type="text/javascript">
                                    function deleteTeacher() {
                                        var Id= arguments[0];
                                        msg='是否删除？';
                                        if(window.confirm(msg)) {
                                            URL="${path}/school/teacher/deleteTeacher/"+Id;
                                            window.location=URL;
                                        }
                                    }
                                </script>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
