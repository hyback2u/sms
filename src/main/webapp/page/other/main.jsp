<%--
  Created by IntelliJ IDEA.
  User: Wang XiaoLong
  Date: 2021/4/24
  Time: 16:19
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../include/header.jsp" %>
<%@ include file="../../include/search.jsp" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<div style="width: 800px;margin: 50px auto;">
    <h1>欢迎进入安徽理工大学校内超市管理系统</h1>
    <br/>
    <ul style="font-size: large;line-height: 28px">
        <li>感谢周华平教授的指导与支持！</li>
        <li>系统：基于Java的超市管理系统</li>
        <li>本系统为@2021年安徽理工大学毕业设计项目</li>
        <li>系统后端基于Spring、SpringMVC、MyBatis、PageHelper构建开发</li>
        <li>系统前端采用BootStrap框架进行快速开发</li>
        <li>系统设计模式：MVC设计模式</li>
    </ul>
</div>
<%@ include file="../../include/footer.jsp" %>
