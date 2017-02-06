<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String project = request.getContextPath();
    request.setAttribute("vEnter", "\n");
	//System.out.println(request.getContextPath());
	String projectTitle = "会员中心";
	String path = project + "/person";
%>