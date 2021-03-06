<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/19
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<html>
<head>
    <title>添加班级</title>
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
                    <small>新增班级</small>
                </h1>
            </div>
        </div>
    </div>
    <form>
        年级<input type="number" name="grade" oninput="if(value.length>4)value=value.slice(0,4)" ><br><br><br>
        班级<input type="number" name="classNumber"><br><br><br>
        <c:forEach var="Course" items="${requestScope.get('courses')}">
            ${Course.name}<select name="course${Course.id}">
            <option value="0">==请选择==</option>
            <c:forEach var="Teacher" items="${requestScope.get('teachers')}" varStatus="var">
                <option value="${Teacher.id}"> ${Teacher.name}</option>
            </c:forEach>
        </select>
        </c:forEach><br><br><br>
        班主任<select name="advisorCourse" id="advisorCourse">
            <option value="0">==请选择==</option>
            <c:forEach var="Course" items="${requestScope.get('courses')}" varStatus="var">
                <option value="${Course.id}" <c:if test="${Course.id==advisorCourse}">selected</c:if>> ${Course.name}老师</option>
            </c:forEach>
        </select><br><br><br>
        <input type="button" value="添加" onclick="addClass()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
        function addClass() {
            var form = document.forms[0];
            form.action = "<%=basePath %>class/addClass";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
