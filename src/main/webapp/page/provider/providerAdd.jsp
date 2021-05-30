<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/5/3
  Time: 22:47
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container" style="width: 50%; alignment: center">
    <div style="width: 80%; margin: 0 auto; height: 40px;">
        <%--路径导航--%>
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li><a href="${APP_PATH}/provider/providerList">供应商详情</a></li>
            <li class="active">添加供应商</li>
        </ol>
    </div>

    <%--中间部门内容--%>
    <div style="width: 80%; alignment: center;">
        <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">
            <h3>添加供货商详情页</h3>
        </div>

        <%--添加供货商表单填写--%>
        <form class="form-horizontal" action="${APP_PATH}/provider/addProvider">
            <%--供货商名称--%>
            <div class="form-group">
                <label for="providerName" class="col-sm-4 control-label">供货商名称</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="providerName"
                           id="providerName" placeholder="供货商名称">
                </div>
            </div>

            <%--供货商联系人--%>
            <div class="form-group">
                <label for="providerContact" class="col-sm-4 control-label">供货商联系人</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="providerContact" id="providerContact"
                           placeholder="供货商联系人">
                </div>
            </div>

            <%--供货商联系电话--%>
            <div class="form-group">
                <label for="contactPhone" class="col-sm-4 control-label">联系电话</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="contactPhone"
                           id="contactPhone" placeholder="联系电话">
                </div>
            </div>

            <%--供货商地址--%>
            <div class="form-group">
                <label for="providerAddress" class="col-sm-4 control-label">供货商地址</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="providerAddress"
                           id="providerAddress" placeholder="供货商地址">
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