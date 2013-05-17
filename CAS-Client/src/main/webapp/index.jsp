<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
你已经登录进来了兄弟，下面是你的登录信息：
<%
    AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

    Map<String, Object> attributes = principal.getAttributes();

    if(attributes != null) {
        for(String key:attributes.keySet()){
%>
<%=attributes.get(key) %>
<%
        }
    }
%>
<p><%=principal.getName() %></p>
<%
    if(session != null){%>
<p><%=session.getId() %></p>
<%}
%>

<a href="https://localhost:8443/cas/logout">退出登录</a>
</body>
</html>