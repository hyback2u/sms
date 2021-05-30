<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 16:48
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <title>系统登录页面</title>
    <%--引入BootStrap核心样式文件--%>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body <%--style="background: url(bg.jpg) no-repeat"--%>>
<div class="container" style="width: 45%; alignment: center; margin-top: 50px;">
    <%--    border: solid 0px black;border-radius: 5px;box-shadow: 2px 2px 10px #909090;    background-color: aliceblue;--%>
    <%--    background-size:100% 100%;position: absolute;top: 40px;right: 20px;--%>
    <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">
        <h1>系统登录</h1>
    </div>

    <div style="width: 80%;alignment: center">
        <form class="form-horizontal" action="${APP_PATH}/login" method="post">
            <div class="form-group">
                <label for="loginName" class="col-sm-4 control-label">用户名</label>
                <div class="col-sm-8">
                    <input type="text" name="loginName" class="form-control"
                           id="loginName" placeholder="请输入用户名">
                </div>
            </div>
            <div class="form-group">
                <label for="loginPassword" class="col-sm-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                <div class="col-sm-8">
                    <input type="password" name="loginPassword" class="form-control"
                           id="loginPassword" placeholder="请输入密码">
                </div>
            </div>
            <%--“记住我”功能暂未开发(2021年5月9日22:11:15)--%>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" name="rm" value="1"> Remember me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="reset" class="btn btn-primary">重置(Reset)</button>
                </div>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-danger">登录(Login In)</button>
                </div>
            </div>
            <div class="col-sm-4 col-sm-offset-4" style="width: 65%;alignment: center">
                <c:if test="${ERROR_INFO != null && ERROR_INFO != ''}">
                    <div class="alert alert-danger" role="alert">
                            ${ERROR_INFO}
                    </div>
                </c:if>
                <c:if test="${requestScope.LOGIN_MSG != null}">
                    <div class="alert alert-danger" role="alert">
                            ${requestScope.LOGIN_MSG}
                    </div>
                </c:if>
            </div>
        </form>
    </div>
</div>
</body>
</html>
