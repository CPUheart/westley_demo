
<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/19
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>更新班级信息</title>
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
                    <small>修改班级信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
            <input type="hidden" name="id" value="${requestScope.get('class').id}"><br><br><br>
        年级<input type="number" name="grade" oninput="if(value.length>4) value=value.slice(0,4)"  value="${requestScope.get('class').grade}"><br><br><br>
        班级<input type="number" name="classNumber" value="${requestScope.get('class').classNumber}"><br><br><br>
        <c:forEach var="Course" items="${requestScope.get('courses')}">
            ${Course.name}<select name="course${Course.id}">
            <c:forEach var="Teacher" items="${requestScope.get('teachers')}" varStatus="var">
                <option value="${Teacher.id}" <c:if test="${(Course.id==1 && Teacher.id==teacher1.teacherId) || (Course.id==2 && Teacher.id==teacher2.teacherId) || (Course.id==3 && Teacher.id==teacher3.teacherId)}">selected</c:if>> ${Teacher.name}</option>
            </c:forEach>
        </select>

        </c:forEach><br><br><br>
        班主任<select name="advisorCourse" id="advisorCourse">
        <c:forEach var="Course" items="${requestScope.get('courses')}" varStatus="var">
            <option value="${Course.id}" <c:if test="${Course.id==advisorCourse}">selected</c:if>> ${Course.name}老师</option>
        </c:forEach>
    </select><br><br><br>
        <input type="button" value="修改" onclick="updateClass()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
        function updateClass() {
            var form = document.forms[0];
            form.action = "<%=basePath %>class/updateClass";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
