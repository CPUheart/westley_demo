<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/26
  Time: 19:19
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
                    <small>成绩列表</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 column">
            <form name="conditionForm" >
                年级<select name="grade" id="grade">
                    <option value="0">==请选择==</option>
                    <c:forEach var="Grade" items="${requestScope.get('gradeList')}" varStatus="var">
                        <option value="${Grade}">${Grade}</option>
                    </c:forEach>
                </select>
                班级<select name="classNumber" id="classNumber">
                    <option value="0">==请选择==</option>
                    <c:forEach var="ClassNumber" items="${requestScope.get('classNumberList')}" varStatus="var">
                        <option value="${ClassNumber}">${ClassNumber}</option>
                    </c:forEach>
                </select>
                课程<select name="courseId" id="courseId">
                <option value="0">==请选择==</option>
                <c:forEach var="Course" items="${requestScope.get('courses')}" varStatus="var">
                    <option value="${Course.id}">${Course.name}</option>
                </c:forEach>
                </select><br>
                <input type="button" value="录入成绩" onclick="toAddScore()"/>
                <input type="button" value="查询" onclick="queryScore()"/>
            </form>
            <script type="text/javascript">
                function toAddScore() {
                    var form = document.forms['conditionForm'];
                    form.action = "<%=basePath%>score/toAddScore";
                    form.method = "post";
                    form.submit();
                }
                function queryScore() {
                    var form = document.forms['conditionForm'];
                    form.action = "<%=basePath%>score/queryScore";
                    form.method = "post";
                    form.submit();
                }
            </script>
        </div>
        <div class="col-md-3 column">
        </div>
        <div class="col-md-3 column" style="float:right">
            <input type="button" value="返回" onclick="history.go(-1);">
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>课程名称</th>
                    <th>成绩</th>
                    <th>班级排名</th>
                    <th>年级排名</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="StudentScore" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <td>${StudentScore.studentId}</td>
                        <td>${StudentScore.studentName}</td>
                        <td>${StudentScore.courseName}</td>
                        <td>${StudentScore.scoreNumber}</td>
                        <td>${StudentScore.rankInClass}</td>
                        <td>${StudentScore.rankInGrade}</td>
                        <td>
                            <a href="${path}/school/class/classInfo?id=${StudentClass.classId}">班级详情</a> |
                            <a href="${path}/school/student/toUpdateStudent?id=${StudentClass.id}">更改</a> |
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
