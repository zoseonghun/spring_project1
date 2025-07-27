<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>성훈</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<%@ include file="common/header.jsp"%>

    <div id="container">
        <div id="menuAdmin">
            <h2 id="menuAdminH2">공지사항</h2>

        <%-- 특별한기능(jstl이라는 라이브러리를 이용해서, 세션에 있는 변수를 셋팅 조건을 겁니다.
             세션공간에 저장되어있는 "MANAGER"의 값이 true일때 작성이란느 버튼이 보이게 함--%>
        <c:if test="${MANAGER == true}">
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/noticeAdd'">작성</button>
        </c:if>
        <div id="menuList">
        </div>
        </div>
    </div>

<%@ include file="common/footer.jsp"%>

</body>
</html>
