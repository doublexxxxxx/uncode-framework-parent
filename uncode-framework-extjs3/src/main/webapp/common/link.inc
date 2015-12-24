<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	String path_$ = request.getContextPath();
	request.setAttribute("path_$", path_$);
%>

<link rel="icon" type="image/x-icon" href="<%=path_$%>/common/favicon.ico">
<link rel="shortcut icon" type="image/x-icon" href="<%=path_$%>/common/favicon.ico">
<link rel="stylesheet" type="text/css" href="<%=path_$%>/extjs/css/jsonSuggest.css" />
<link rel="stylesheet" type="text/css" href="<%=path_$%>/extjs/css/layout/main.css" />
<link rel="stylesheet" type="text/css" href="<%=path_$%>/extjs/css/ext-all.css" />
<script type="text/javascript" src="<%=path_$%>/extjs/ext-base.js"></script>
<script type="text/javascript" src="<%=path_$%>/extjs/ext-all.js"></script>