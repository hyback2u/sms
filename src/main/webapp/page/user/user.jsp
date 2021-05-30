<%--
    Description: 管理员身份下, 进行用户管理的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script>
    /*重置所有用户密码*/
    function resetAllPassword() {
        if (confirm("您确定重置所有用户的密码吗？")) {
            location.href = "${APP_PATH}/resetAllPassword";
        }
    }

    /*重置选中用户的密码*/
    function resetSelectedUserPassword(id, username) {
        if (confirm("您确定重置【" + username + "】的密码吗？")) {
            location.href = "${APP_PATH}/resetSelectedUserPassword?id=" + id;
        }
    }

    /*删除选中用户*/
    function deleteSelectedUser(id, username) {
        if (confirm("您确定注销用户【" + username + "】吗？")) {
            location.href = "${APP_PATH}/deleteSelectedUser?id=" + id;
        }
    }

</script>

<%--中间内容部分--%>
<div class="container">
    <%--路径导航与搜索框--%>
    <div style="width: 65%; margin: 0 auto; height: 100px;">
        <%--路径导航--%>
        <ol class="breadcrumb">
            <li><a href="${APP_PATH}/home">首页</a></li>
            <li class="active">用户管理</li>
        </ol>

        <a href="${APP_PATH}/toAddUser" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增用户
        </a>
        <a href="javascript:resetAllPassword();" class="btn btn-danger">
            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;重置所有密码
        </a>
    </div>

    <%--用户信息表格--%>
    <div style="width: 65%; margin: 0 auto; height: 400px;">
        <%--表格样式: 基础表格、带边框、悬浮隔行变色--%>
        <table class="table table-bordered table-hover">
            <thead>
            <tr style="background: #f1f1ef; alignment: center">
                <th>用户编号</th>
                <th>账号</th>
                <th>密码</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.role == "admin"? "管理员":"普通用户"}</td>
                    <td>
                        <a type="button" class="btn btn-primary btn-sm" aria-label="Left Align"
                           href="javascript:resetSelectedUserPassword(`${user.id}`,`${user.username}`);">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 重置密码
                        </a>
                        <a type="button" class="btn btn-danger btn-sm" aria-label="Left Align"
                           href="javascript:deleteSelectedUser(`${user.id}`,`${user.username}`);">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 注销用户
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
                        <li><a href="${APP_PATH}/userList?pn=1">首页</a></li>
                        <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/userList?pn=${pageInfo.pageNum-1}"
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
                                <li><a href="${APP_PATH}/userList?pn=${page_Num}">${page_Num}</a></li>
                            </c:if>
                        </c:forEach>

                        <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/userList?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <li><a href="${APP_PATH}/userList?pn=${pageInfo.pages}">末页</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../include/footer.jsp" %>