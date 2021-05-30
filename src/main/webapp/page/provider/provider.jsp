<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 17:17
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>

<script>
    // 显示该供应商的供货销售情况
    function showProduct(providerId) {
        location.href = "${APP_PATH}/provider/provideProducts?providerId=" + providerId;
    }

    // 供应商删除按钮
    function deleteProvider(providerId, providerName) {
        if (confirm("您确定要删除供应商 【" + providerName + "】 吗？")) {
            // 访问路径
            location.href = "${APP_PATH}/provider/deleteProvider?providerId=" + providerId;
        }
    }
</script>

<div class="container">
    <%--路径导航与搜索框--%>
    <div style="width: 100%; margin: 0 auto; height: 100px;">
        <%--路径导航--%>
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li class="active">供应商详情</li>
        </ol>

        <%--搜索框--%>
        <form class="form-inline" action="${APP_PATH}/provider/searchProvider" method="post">
            <div class="form-group">
                <label for="phoneNumber">联系人电话</label>
                <input type="text" name="phoneNumber" class="form-control"
                       id="phoneNumber" placeholder="联系人电话">
            </div>
            &nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;开始查找
            </button>
            &nbsp;&nbsp;&nbsp;
            <a href="${APP_PATH}/provider/toAddProviderPage" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增供应商
            </a>
        </form>
    </div>

    <%--商品库存信息表格--%>
    <div style="width: 100%; margin: 0 auto; height: 400px;">
        <%--表格样式: 基础表格、带边框、悬浮隔行变色--%>
        <table class="table table-bordered table-hover">
            <thead>
            <tr style="background: #f1f1ef; alignment: center">
                <th>供应商编码</th>
                <th>供应商名称</th>
                <th>供应商联系人</th>
                <th>供应商联系电话</th>
                <th>供应商地址</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="provider">
                <tr style="font-size: xx-small">
                    <td>${provider.id}</td>
                    <td>${provider.providerName}</td>
                    <td>${provider.providerContact}</td>
                    <td>${provider.contactPhone}</td>
                    <td>${provider.providerAddress}</td>
                        <%--这两处操作的难点就是, 如何将选中的条目的obj对象的id获得并传出去以便按照id删除--%>
                    <td>
                        <a type="button" class="btn btn-info btn-sm" aria-label="Left Align"
                           href="javascript:showProduct(${provider.id});">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span> 供货信息
                        </a>
                        <a type="button" class="btn btn-primary btn-sm" aria-label="Left Align"
                           href="${APP_PATH}/provider/toUpdateProviderPage?providerId=${provider.id}">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改
                        </a>
                        <a type="button" class="btn btn-danger btn-sm" aria-label="Left Align"
                           href="javascript:deleteProvider(`${provider.id}`,`${provider.providerName}`)">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除
                        </a>
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
                        <li><a href="${APP_PATH}/provider/providerList?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/provider/providerList?pn=${pageInfo.pageNum-1}"
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
                                    <a href="${APP_PATH}/provider/providerList?pn=${page_Num}">${page_Num}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/provider/providerList?pn=${pageInfo.pageNum+1}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li>
                            <a href="${APP_PATH}/provider/providerList?pn=${pageInfo.pages}">末页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/footer.jsp" %>