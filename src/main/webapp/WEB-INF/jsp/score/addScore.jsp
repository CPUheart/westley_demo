<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/27
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>录入成绩</title>
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
                <c:forEach var="Grade" items="${requestScope.get('gradeList')}" varStatus="var">
                    <option value="${Grade}" <c:if test="${Grade==selectedGrade}">selected</c:if> >${Grade}</option>
                </c:forEach>
                </select>
                班级<select name="classNumber" id="classNumber">
                <c:forEach var="ClassNumber" items="${requestScope.get('classNumberList')}" varStatus="var">
                    <option value="${ClassNumber}" <c:if test="${ClassNumber==selectedClassNumber}">selected</c:if> >${ClassNumber}</option>
                </c:forEach>
                </select>
                课程<select name="courseId" id="courseId">
                <c:forEach var="Course" items="${requestScope.get('courses')}" varStatus="var">
                    <option value="${Course.id}"  <c:if test="${Course.id==selectedCourse.id}">selected</c:if>>${Course.name}</option>
                </c:forEach>
                </select><br>
                <input type="button" value="录入成绩" onclick="toAddScore()"/>
            </form>
            <script type="text/javascript">
                function toAddScore() {
                    var form = document.forms['conditionForm'];
                    form.action = "<%=basePath%>score/toAddScore";
                    form.method = "post";
                    form.submit();
                }
            </script>
        </div>
        <div class="col-md-3 column">
        </div>
        <div class="col-md-3 column" style="float:right">
            <input type="button" value="返回" onclick="window.location.href='${path}/school/score/allScore'"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>成绩</th>
                    <th>操作</th>
                </thead>
                <tbody>
                    <c:forEach var="StudentScore" items="${requestScope.get('list')}" varStatus="status">
                        <tr>
                            <form name="scoreForm">
                                <td hidden><input type="text" name="courseId" value="${StudentScore.courseId}"></td>
                                <td><input type="text" name="studentId" value="${StudentScore.studentId}" readonly></td>
                                <td><input type="text" value="${StudentScore.studentName}" disabled></td>
                                <td><input type="number" name="scoreNumber" oninput="if(value>100 || value<0){alert('非法输入！');value=''}"></td>
                                <td><input type="button" value="录入" onclick="addScore()"/></td>
                            </form>
                            <script type="text/javascript">
                                function addScore() {
                                    var form = document.forms['scoreForm'];
                                    form.action = "<%=basePath%>score/addScore";
                                    form.method = "post";
                                    form.submit();
                                }
                            </script>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
