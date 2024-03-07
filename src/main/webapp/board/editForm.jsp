<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="dao" class="board.BoardDAO"/>

<% 
	int num = Integer.parseInt(request.getParameter("num"));
	BoardVO vo = dao.selectOne(num);
	pageContext.setAttribute("vo", vo);	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
<h3>수정하기</h3>
<form action="edit.jsp" method="post">
	<input type="hidden" name="num" value="${vo.num}">		
	<input type="text" name="title" value="${vo.title}" required><br>
	<input type="text" name="writer" value="${vo.writer}" required disabled><br>
	<textarea rows="4" cols="20" name="content">${vo.content}</textarea><br>
	<input type="submit" value="수정">	
</form>
</body>
</html>