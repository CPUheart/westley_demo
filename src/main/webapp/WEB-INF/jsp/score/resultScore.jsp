<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/27
  Time: 16:42
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
                课程<select name="courseId" id="courseId">
                    <c:forEach var="Course" items="${requestScope.get('courses')}" varStatus="var">
                        <option value="${Course.id}"  <c:if test="${Course.id==selectedCourse.id}">selected</c:if>>${Course.name}</option>
                    </c:forEach>
                </select>
                年级<select name="grade" id="grade">
                    <c:forEach var="Grade" items="${requestScope.get('gradeList')}" varStatus="var">
                        <option value="${Grade}" <c:if test="${Grade==selectedGrade}">selected</c:if> >${Grade}</option>
                    </c:forEach>
                </select>
                班级<select name="classNumber" id="classNumber">
                    <option value="0">==请选择==</option>
                    <c:forEach var="ClassNumber" items="${requestScope.get('classNumberList')}" varStatus="var">
                        <option value="${ClassNumber}" <c:if test="${ClassNumber==selectedClassNumber}">selected</c:if> >${ClassNumber}</option>
                    </c:forEach>
                </select><br>
                <input type="button" value="查询" onclick="queryScore()"/>
            </form>
            <script type="text/javascript">
                function queryScore() {
                    var form = document.forms['conditionForm'];
                    var a = form['courseId'];
                    var b = form['grade'];
                    if(a.value==0 || b.value==0) {
                        window.alert("请选择课程和年级！");
                        return false;
                    }
                    form.action = "<%=basePath%>score/queryScore";
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
                <th>班级排名</th>
                <th>年级排名</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach var="StudentScore" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                        <form name="scoreForm">
                            <td hidden><input type="text" name="courseId" value="${StudentScore.courseId}"></td>
                            <td><input type="text" name="studentId" value="${StudentScore.studentId}" readonly></td>
                            <td><input type="text" value="${StudentScore.studentName}" disabled></td>
                            <td><input type="number" name="scoreNumber" value="${StudentScore.scoreNumber}" oninput="if(value>100 || value<0){alert('非法输入！');value=''}"></td>
                            <td><input type="text" name="rankInClass" value="${StudentScore.rankInClass}" readonly /></td>
                            <td><input type="text" name="rankInGrade" value="${StudentScore.rankInGrade}" readonly/></td>
                            <td>
                                <input type="button" value="修改" onclick="updateScore()">
                                <a href="javascript:deleteScore('${StudentScore.studentId}','${StudentScore.courseName}')">删除</a>
                                <script type="text/javascript">
                                    function updateScore() {
                                        var form = document.forms['scoreForm'];
                                        form.action = "<%=basePath %>score/updateScore";
                                        form.method = "post";
                                        form.submit();
                                    }
                                    function deleteScore() {
                                        var id= arguments[0];
                                        var name=arguments[1];
                                        msg='是否删除？';
                                        if(window.confirm(msg)) {
                                            URL="${path}/school/score/deleteScore?studentId="+id+"&courseName="+name;
                                            window.location=URL;
                                        }
                                    }
                                </script>
                            </td>

                        </form>
                        <script type="text/javascript">
                            function updateScore() {
                                var form = document.forms['scoreForm'];
                                form.action = "<%=basePath%>score/updateScore";
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
