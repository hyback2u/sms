<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 16:16
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script>
    function deleteGroup(groupId, groupName) {
        if (confirm("您确定要删除【" + groupName + "】吗？")) {
            // 访问路径
            location.href = "${APP_PATH}/group/deleteGroup?groupId=" + groupId;
        }
    }
</script>
<div class="container">
    <%--导航栏--%>
    <div style="width: 60%; margin: 0 auto; height: 100px;">
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li class="active">商品分类</li>
        </ol>
        <form class="form-inline" action="#" method="post">
            <a href="${APP_PATH}/group/addGroupPage" class="btn btn-primary">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增商品分类
            </a>
        </form>
    </div>

    <%--商品分类表格--%>
    <div style="width: 60%; margin: 0 auto; height: 400px;">

        <%--表格(开始) 样式: 基础表格、带边框、悬浮隔行变色--%>
        <table class="table table-bordered table-hover" style="text-align: center">
            <thead>
            <tr style="background: #f1f1ef;">
                <th>编号</th>
                <th>类别</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="group">
                <tr>
                    <td>${group.id}</td>
                    <td>${group.groupName}</td>
                    <td>
                        <a type="button" class="btn btn-primary btn-sm" aria-label="Left Align"
                           href="${APP_PATH}/group/toUpdateGroupPage?id=${group.id}">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改分类
                        </a>
                        <a type="button" class="btn btn-danger btn-sm" aria-label="Left Align"
                           href="javascript:deleteGroup(`${group.id}`,`${group.groupName}`);">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除分类
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--表格(结束)--%>

        <%--分页信息(开始)--%>
        <div class="row">
            <%--分页数据显示--%>
            <div class="col-md-6">
                <b>当前第 ${pageInfo.pageNum} 页, 总 ${pageInfo.pages} 页, 总 ${pageInfo.total} 条记录</b>
            </div>
            <%--分页条--%>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%--自定义添加首页末页--%>
                        <li><a href="${APP_PATH}/group/groupList?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/group/groupList?pn=${pageInfo.pageNum-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                            <c:if test="${page_Num==pageInfo.pageNum}">
                                <li class="active"><a href="#">${page_Num}</a></li>
                            </c:if>
                            <c:if test="${page_Num!=pageInfo.pageNum}">
                                <li><a href="${APP_PATH}/group/groupList?pn=${page_Num}">${page_Num}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/group/groupList?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li><a href="${APP_PATH}/group/groupList?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
        <%--分页信息(结束)--%>
    </div>
</div>
<%@ include file="../../include/footer.jsp" %>