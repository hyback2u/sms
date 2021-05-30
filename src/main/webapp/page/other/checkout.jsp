<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 16:20
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container">
    <%--路径导航--%>
    <div style="width: 80%; margin: 0 auto; height: 100px;">
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li class="active">结算系统</li>
        </ol>
        <h3>收银台服务</h3>
    </div>

    <%--已购商品输入框--%>
    <div style="width: 80%; margin: 0 auto; height: 30px;">
        <form class="form-inline" action="${APP_PATH}/product/checkPlusPro" method="post">
            <div class="form-group">
                <label for="productId">商品编号</label>
                <input type="text" class="form-control" id="productId" name="productId" placeholder="输入商品编号">
            </div>
            &nbsp;
            <div class="form-group">
                <label for="productCount">购买数量</label>
                <input type="text" class="form-control" id="productCount" name="productNumber" placeholder="输入购买数量">
            </div>
            &nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-random" aria-hidden="true"></span>&nbsp;结算(Check)
            </button>
        </form>
    </div>
    <br/>

    <%--商品详细结算信息--%>
    <div style="width: 80%; margin: 0 auto; height: 100px;">
        <h3>详细信息如下：</h3>
        <c:if test="${checkObj.productNumber!=null}">
            <form action="" method="post">
                <h4>
                    商品名称：${checkObj.productName} &nbsp;&nbsp;&nbsp;
                    销售单价：${checkObj.productSalePrice} &nbsp;&nbsp;&nbsp;
                    购买数量：${checkObj.productNumber}
                </h4>
                <h4>
                    应付金额：${checkObj.checkPrice} &nbsp;&nbsp;&nbsp;
                    实收金额：
                    <label for="payMoney"></label>
                    <input type="text" id="payMoney" name="payMoney" placeholder="输入实收金额">
                    <input type="button" onclick="checkMoney(${checkObj.checkPrice})" class="btn btn-primary"
                           value="计算找零">
                    <br/>
                    <h4 style="color: red" id="showReturnMoney">找零：xxx 元</h4>
                </h4>
            </form>
        </c:if>
    </div>
</div>

<script>
    // 用于计算找零服务
    function checkMoney(event) {
        let getMoney = document.getElementById("payMoney").value;
        document.getElementById("showReturnMoney").innerHTML = "找零：" + (getMoney - event) + " 元"
    }
</script>

<%@ include file="../../include/footer.jsp" %>
