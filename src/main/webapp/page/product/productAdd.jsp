<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container" style="width: 50%; alignment: center">

    <%--中间部门内容--%>
    <div style="width: 80%; alignment: center;">
        <%--表单内容太多, 标题就不写了--%>
        <%--        <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">--%>
        <%--            <h3>添加商品详情页</h3>--%>
        <%--        </div>--%>

        <form class="form-horizontal" action="${APP_PATH}/product/addProduct">
            <%--商品名称--%>
            <div class="form-group">
                <label for="productName" class="col-sm-4 control-label">商品名称</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="productName" id="productName" placeholder="商品名称">
                </div>
            </div>

            <%--商品类别 v2--%>
            <div class="form-group">
                <label class="col-sm-4 control-label">商品类别</label>
                <div class="col-sm-8">
                    <label>
                        <select class="form-control" name="groupId">
                            <c:forEach items="${allGroups}" var="group">
                                <option value="${group.id}">${group.groupName}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>

            <%--供货商--%>
            <div class="form-group">
                <label class="col-sm-4 control-label">供货商</label>
                <div class="col-sm-8">
                    <label>
                        <select class="form-control" name="providerId">
                            <c:forEach items="${allProviders}" var="provider">
                                <option value="${provider.id}">${provider.providerName}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>

            <%--生产日期--%>
            <div class="form-group">
                <label for="yieldDate" class="col-sm-4 control-label">生产日期</label>
                <div class="col-sm-8">
                    <input type="date" class="form-control" name="yieldDate" id="yieldDate" placeholder="生产日期">
                </div>
            </div>

            <%--生产厂家--%>
            <div class="form-group">
                <label for="productFactory" class="col-sm-4 control-label">生产厂家</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="productFactory" id="productFactory"
                           placeholder="生产厂家">
                </div>
            </div>

            <%--进价--%>
            <div class="form-group">
                <label for="purchasePrice" class="col-sm-4 control-label">进价</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="purchasePrice" id="purchasePrice"
                           placeholder="进价">
                </div>
            </div>

            <%--库存--%>
            <div class="form-group">
                <label for="stock" class="col-sm-4 control-label">库存</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="stock" id="stock" placeholder="库存">
                </div>
            </div>

            <%--售价--%>
            <div class="form-group">
                <label for="salePrice" class="col-sm-4 control-label">售价</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="salePrice" id="salePrice" placeholder="售价">
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