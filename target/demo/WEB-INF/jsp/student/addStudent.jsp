<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/20
  Time: 15:30
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
    <title>添加学生</title>
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
                    <small>新增学生</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
        学号<input type="number" name="id" oninput="if(value.length>9)value=value.slice(0,9)" ><br><br><br>
        姓名<input type="text" name="name" maxlength="15"><br><br><br>
        <%--性别<input type="text" name="gender"><br><br><br>--%>
        性别<input type="radio" name="gender" value="男" checked="checked">男 <input type="radio" name="gender" value="女">女<br><br><br>
       <%-- 年级<input type="number" name="grade" oninput="if(value.length>4)value=value.slice(0,4)" ><br><br><br>
        班级<input type="number" name="classNumber"><br><br><br>--%>
        年级<select name="grade" id="grade">
            <option value="0">==请选择==</option>
            <c:forEach var="Grade" items="${requestScope.get('gradeList')}" varStatus="var">
                <option value="${Grade}"> ${Grade}</option>
            </c:forEach>
        </select><br><br><br>
        班级<select name="classNumber" id="classNumber">
            <option value="0">==请选择==</option>
            <c:forEach var="number" items="${requestScope.get('classNumberList')}">
                <option value="${number}">${number}</option>
            </c:forEach>
        </select><br><br><br>
        <input type="button" value="添加" onclick="addStudent()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
            function addStudent() {
            var form = document.forms[0];
            form.action = "<%=basePath %>student/addStudent";
            form.method = "post";
            form.submit();
        }
    </script>
</div>

</body>
</html>
