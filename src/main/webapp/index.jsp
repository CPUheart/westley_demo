<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
            font-size: 18px;
        }

        h3 {
            width: 180px;
            height: 38px;
            margin: 50px auto;
            text-align: center;
            line-height: 38px;
            background: deepskyblue;
            border-radius: 4px;
        }
    </style>
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
</div>
<br><br>
<h3>
    <a href="${path}/class/allClass">班级信息管理</a>
</h3>
<h3>
    <a href="${path}/student/allStudent">学生信息管理</a>
</h3>
<h3>
    <a href="${path}/teacher/allTeacher">老师信息管理</a>
</h3>
<h3>
    <a href="${path}/score/allScore">考试信息管理</a>
</h3>
</body>
</html>
