<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/20
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>更改学生信息</title>
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
                    <small>更改学生信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="" name="userForm">
        学号<input type="text" readonly="true" name="id" oninput="if(value.length>9)value=value.slice(0,9)"  value="${requestScope.get('id')}"><br><br><br>
        姓名<input type="text" name="name" maxlength="30" value="${requestScope.get('name')}"><br><br><br>
        性别
            <input type="radio" name="gender" value="男" <c:if test="${gender=='男'}">checked="checked"</c:if>>男
            <input type="radio" name="gender" value="女" <c:if test="${gender=='女'}">checked="checked"</c:if>>女
        <br><br><br>
        <%--性别<input type="text" name="gender" value="${requestScope.get('studentClass').gender}"><br><br><br>--%>
        <%--年级<input type="number" name="grade" oninput="if(value.length>4)value=value.slice(0,4)"  value="${requestScope.get('grade')}"><br><br><br>
        班级<input type="number" name="classNumber" value="${requestScope.get('classNumber')}"><br><br><br>--%>
        年级<select name="grade" id="grade">
        <c:forEach var="Grade" items="${requestScope.get('gradeList')}" varStatus="var">
            <option value="${Grade}" <c:if test="${Grade==grade}">selected</c:if>> ${Grade}</option>
        </c:forEach>
    </select><br><br><br>
        班级<select name="classNumber" id="classNumber">
        <c:forEach var="number" items="${requestScope.get('classNumberList')}">
            <option value="${number}" <c:if test="${number==classNumber}">selected</c:if>>${number}</option>
        </c:forEach>
    </select><br><br><br>
        <input type="button" value="修改" onclick="updateStudent()">
        <input type="button" value="返回" onclick="history.go(-1);">
    </form>
    <script type="text/javascript">
        function updateStudent() {
            var form = document.forms[0];
            form.action = "<%=basePath %>student/updateStudent";
            form.method = "post";
            form.submit();
        }
    </script>
</div>
</body>
</html>
