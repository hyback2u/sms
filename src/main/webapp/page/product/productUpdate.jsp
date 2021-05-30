<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container" style="width: 50%; alignment: center">

    <%--中间部门内容--%>
    <div style="width: 80%; alignment: center;">
        <%--        <div class="col-sm-4 col-sm-offset-4" style="width: 80%; alignment: center">--%>
        <%--            <h3>添加、修改商品详情页</h3>--%>
        <%--        </div>--%>

        <form class="form-horizontal" action="${APP_PATH}/product/updateProductByProductId">
            <%--商品id--%>
            <%--如果想要属性不被修改, 这里要设置readonly, disable会导致不可读, 读不到数据后端根据id更新个锤子--%>
            <div class="form-group">
                <label for="id" class="col-sm-4 control-label">商品id</label>
                <div class="col-sm-8">
                    <input type="text" readonly="readonly" class="form-control" name="id" id="id"
                           placeholder="${selectedProduct.id}"
                           value="${selectedProduct.id}">
                </div>
            </div>

            <%--商品名称--%>
            <div class="form-group">
                <label for="productName" class="col-sm-4 control-label">商品名称</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="productName" id="productName"
                           placeholder="${selectedProduct.productName}"
                           value="${selectedProduct.productName}">
                </div>
            </div>
            <%--商品类别--%>
            <div class="form-group">
                <label class="col-sm-4 control-label">商品类别</label>
                <div class="col-sm-8">
                    <label>
                        <select class="form-control" name="groupId">
                            <option selected value="${selectedProduct.groupId}">
                                ${selectedProduct.group.groupName}</option>
                            <c:forEach items="${allGroups}" var="group">
                                <option value="${group.id}">${group.groupName}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>

            <%--生产日期--%>
            <div class="form-group">
                <label for="yieldDate" class="col-sm-4 control-label">生产日期</label>
                <div class="col-sm-8">
                    <input type="date" class="form-control" name="yieldDate" id="yieldDate"
                           placeholder="${selectedProduct.yieldDate}"
                           value="${selectedProduct.yieldDate}">
                </div>
            </div>

            <%--生产厂家--%>
            <div class="form-group">
                <label for="productFactory" class="col-sm-4 control-label">生产厂家</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="productFactory" id="productFactory"
                           placeholder="${selectedProduct.productFactory}"
                           value="${selectedProduct.productFactory}">
                </div>
            </div>

            <%--进价--%>
            <div class="form-group">
                <label for="purchasePrice" class="col-sm-4 control-label">进价</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="purchasePrice" id="purchasePrice"
                           placeholder="${selectedProduct.purchasePrice}"
                           value="${selectedProduct.purchasePrice}">
                </div>
            </div>

            <%--库存--%>
            <div class="form-group">
                <label for="stock" class="col-sm-4 control-label">库存</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="stock" id="stock"
                           placeholder="${selectedProduct.stock}"
                           value="${selectedProduct.stock}">
                </div>
            </div>

            <%--售价--%>
            <div class="form-group">
                <label for="salePrice" class="col-sm-4 control-label">售价</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" name="salePrice" id="salePrice"
                           placeholder="${selectedProduct.salePrice}"
                           value="${selectedProduct.salePrice}">
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