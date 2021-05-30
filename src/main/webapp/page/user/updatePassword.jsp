<%--
    Description: 更新当前登录用户的密码
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container" style="width: 50%; alignment: center">
    <%--导航栏--%>
    <div style="width: 80%; margin: 0 auto; height: 35px;">
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li><a href="${APP_PATH}/userList">用户管理</a></li>
            <li class="active">修改密码</li>
        </ol>
    </div>

    <%--中间部门内容--%>
    <div style="width: 80%; alignment: center;">
        <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">
            <h3>修改密码</h3>
        </div>

        <form class="form-horizontal" method="post" action="${APP_PATH}/updateCurrentUserPassword">
            <div class="form-group">
                <label for="username" class="col-sm-4 control-label">用户名</label>
                <div class="col-sm-8">
                    <input type="text" readonly="readonly" class="form-control" name="username" id="username"
                           placeholder="${sessionScope.user.username}"
                           value="${sessionScope.user.username}">
                </div>
            </div>

            <div class="form-group">
                <label for="role" class="col-sm-4 control-label">角色</label>
                <div class="col-sm-8">
                    <input type="text" readonly="readonly" class="form-control" name="role" id="role"
                           placeholder="${sessionScope.user.role == "admin"? "管理员":"普通用户"}"
                           value="${sessionScope.user.role == "admin"? "管理员":"普通用户"}">
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control"
                           name="password" id="password" placeholder="请输入新密码">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="reset" class="btn btn-primary">重置(Reset)</button>
                </div>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-danger">提交(Submit)</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="../../include/footer.jsp" %>