<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/5/3
  Time: 1:05
  Description: 供应商详情页面, 点击查看供应商, 显示出该供应商的供货销售情况页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<%--中间内容部分--%>
<div class="container">

    <%--路径导航--%>
    <div style="width: 100%; margin: 0 auto; height: 100px;">
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li><a href="${APP_PATH}/provider/providerList">供应商详情</a></li>
            <li class="active">供应商供货销售信息</li>
        </ol>
        <h2>供应商联系人：${provider.providerContact}，供应商：${provider.providerName}</h2>
    </div>

    <%--商品库存信息表格--%>
    <div style="width: 100%; margin: 0 auto; height: 400px;">

        <%--表格样式: 基础表格、带边框、悬浮隔行变色--%>
        <table class="table table-bordered table-hover">
            <thead>
            <tr style="background: #f1f1ef; alignment: center">
                <th>供应商名称</th>
                <th>商品名称</th>
                <th>分类</th>
                <th>进价</th>
                <th>售价</th>
                <th>售出数</th>
                <th>库存数</th>
                <th>总利润</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="record">
                <tr style="font-size: xx-small">
                    <td>${provider.providerName}</td>
                    <td>${record.productName}</td>
                    <td>${record.group.groupName}</td>
                    <td>${record.purchasePrice}</td>
                    <td>${record.salePrice}</td>
                    <td>${record.saleCount}</td>
                    <td>${record.stock}</td>
                    <td>
                        <c:if test="${record.saleCount==0}">
                            0
                        </c:if>
                        <c:if test="${record.saleCount!=0}">
                            <fmt:formatNumber
                                    value="${(record.salePrice - record.purchasePrice) * record.saleCount}"
                                    pattern="#.00#"/>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%--分页信息--%>
        <div class="row">
            <%--分页数据显示--%>
            <div class="col-md-4">
                <b>当前第 ${pageInfo.pageNum} 页, 总 ${pageInfo.pages} 页, 总 ${pageInfo.total} 条记录</b>
            </div>
            <%--分页条--%>
            <div class="col-md-4 col-md-offset-4">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%--自定义添加首页末页--%>
                        <li><a href="${APP_PATH}/provider/provideProducts?pn=1&providerId=${provider.id}">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/provider/provideProducts?pn=${pageInfo.pageNum-1}&providerId=${provider.id}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <%--分页条BAR--%>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <c:if test="${page_Num==pageInfo.pageNum}">
                                <li class="active"><a href="#">${page_Num}</a></li>
                            </c:if>
                            <c:if test="${page_Num!=pageInfo.pageNum}">
                                <li>
                                    <a href="${APP_PATH}/provider/provideProducts?pn=${page_Num}&providerId=${provider.id}">${page_Num}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/provider/provideProducts?pn=${pageInfo.pageNum+1}&providerId=${provider.id}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${APP_PATH}/provider/provideProducts?pn=${pageInfo.pages}&providerId=${provider.id}">末页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

    </div>

</div>

<%@ include file="../../include/footer.jsp" %>