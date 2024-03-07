<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="vo" class="board.BoardVO"/>
<jsp:useBean id="dao" class="board.BoardDAO"/>
<jsp:setProperty name="vo" property="*"/>
<%
	dao.insert(vo);
	//response.sendRedirect(request.getContextPath() + "/list.jsp");
%>
<c:redirect url="${pageContext.request.contextPath}/list.jsp"/>