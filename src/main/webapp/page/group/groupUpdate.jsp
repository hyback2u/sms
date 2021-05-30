<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/5/1
  Time: 23:09
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
            <li><a href="${APP_PATH}/group/groupList">商品分类</a></li>
            <li class="active">修改商品分类</li>
        </ol>
    </div>

    <%--中间部门内容--%>
    <div style="width: 80%; alignment: center;">
        <%--h3标题--%>
        <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">
            <h3>修改分类详情页</h3>
        </div>

        <%--修改商品分类表单--%>
        <form class="form-horizontal" action="${APP_PATH}/group/updateGroup">
            <%--类别id--%>
            <div class="form-group">
                <label for="id" class="col-sm-4 control-label">类别id</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="id" id="id" readOnly="readonly"
                           placeholder="${group.id}" value="${group.id}">
                </div>
            </div>
            <%--商品类别--%>
            <div class="form-group">
                <label for="groupName" class="col-sm-4 control-label">商品类别</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="groupName" id="groupName"
                           placeholder="${group.groupName}" value="${group.groupName}">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="reset" class="btn btn-primary">重置(Reset)</button>
                </div>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-danger">修改(Update)</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="../../include/footer.jsp" %>