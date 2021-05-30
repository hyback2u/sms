<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 16:36
  Description: 销售统计功能页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<div class="container">
    <!--搜索框-->
    <div style="width: 80%; margin: 0 auto; height: 100px;">
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li class="active">销售统计</li>
        </ol>
        <form class="form-inline" action="${APP_PATH}/sale/saleInfoPro" method="post">
            <div class="form-group">
                <label for="productGroup">商品类别</label>
                <label>
                    <select id="productGroup" class="form-control" name="groupIdInForm">
                        <option value="0" selected>请选择分类</option>
                        <c:forEach items="${allGroups}" var="group">
                            <option value="${group.id}">${group.groupName}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            &nbsp;&nbsp;&nbsp;
            <div class="form-group">
                <label for="startDate">开始时间</label>
                <input type="date" class="form-control" id="startDate" name="startDate">
            </div>
            &nbsp;&nbsp;&nbsp;
            <div class="form-group">
                <label for="endDate">结束时间</label>
                <input type="date" class="form-control" id="endDate" name="endDate">
            </div>

            &nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-yen" aria-hidden="true"></span>&nbsp;统计
            </button>
        </form>
    </div>

    <!--商品信息表格-->
    <div style="width: 80%; margin: 0 auto; height: 400px;">
        <!--样式: 基础表格、带边框、悬浮隔行变色-->
        <table class="table table-bordered table-hover">
            <thead>
            <tr style="background: #f1f1ef;">
                <th>商品编号</th>
                <th>商品名称</th>
                <th>所属分类</th>
                <th>进价</th>
                <th>售价</th>
                <th>销量</th>
                <th>净利润</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${pageInfo != null}">
                <c:forEach items="${pageInfo.list}" var="sale">
                    <tr>
                        <td>${sale.productId}</td>
                        <td>${sale.product.productName}</td>
                        <td>${sale.product.group.groupName}</td>
                        <td>${sale.product.purchasePrice}</td>
                        <td>${sale.product.salePrice}</td>
                        <td>${sale.saleCount}</td>
                        <td>${sale.netProfit}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>

        <%--分页信息--%>
        <div class="row">
            <%--分页数据显示--%>
            <div class="col-md-5">
                <b>当前第 ${pageInfo.pageNum} 页, 总 ${pageInfo.pages} 页, 总 ${pageInfo.total} 条记录</b>
            </div>

            <%--分页条--%>
            <div class="col-md-5 col-md-offset-2">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%--自定义添加首页末页--%>
                        <li><a href="${APP_PATH}/sale/saleInfoPro?pn=1&groupId=${groupId}">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/sale/saleInfoPro?pn=${pageInfo.pageNum-1}&groupId=${groupId}"
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
                                <li><a href="${APP_PATH}/sale/saleInfoPro?pn=${page_Num}&groupId=${groupId}">${page_Num}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/sale/saleInfoPro?pn=${pageInfo.pageNum+1}&groupId=${groupId}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li><a href="${APP_PATH}/sale/saleInfoPro?pn=${pageInfo.pages}&groupId=${groupId}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/footer.jsp" %>