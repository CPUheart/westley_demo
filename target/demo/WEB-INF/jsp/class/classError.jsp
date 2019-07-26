<%--
  Created by IntelliJ IDEA.
  User: WF
  Date: 2019/7/22
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
</head>
<body>
<div>
    <h2>
        <c:if test="${ErrorCode==1}">${grade}级${classNumber}班添加失败，原因是该班级已经存在</c:if>
        <c:if test="${ErrorCode==2}">${grade}级${classNumber}班删除失败，原因是该班级还有学生</c:if>
        <c:if test="${ErrorCode==3}">${grade}级${classNumber}班添加失败，原因是班主任未选择</c:if>
        <c:if test="${ErrorCode==4}">${grade}级${classNumber}班添加失败，原因是班主任${advisorId}已担任其他班班主任职务</c:if>
        <c:if test="${ErrorCode==5}">${grade}级${classNumber}班更改失败，原因是该班级已经存在</c:if>
        <c:if test="${ErrorCode==6}">${grade}级${classNumber}班添加失败，原因是班主任不在该班级任教</c:if>
        <c:if test="${ErrorCode==7}">${grade}级${classNumber}班添加失败，原因是任课教师重复</c:if>
    </h2>
</div>
<div>
    <h2>
        <%--<a href="${path}/school/class/allClass">返回</a>--%>
        <input type="button" value="返回" onclick="history.go(-1);">
    </h2>
</div>
</body>
</html>
