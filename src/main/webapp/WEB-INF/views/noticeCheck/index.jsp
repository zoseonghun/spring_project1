<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항조회</title>
    <meta name="_csrf" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/noticeCheck/style.css">
</head>
<body>

<%@ include file="../common/header.jsp"%>

<%-- javascript 코드로 form 테그의 action 기능을 대신하는 기능을 만든다. restapi 개발방식의 일부분--%>
<form id="menuForm">
    <div id="container">
        <div id="menuAdmin">
            <h2 id="menuAdminH2">공지사항 조회</h2>
            <label for="memID">회원아이디</label>
            <input type="hidden" id="idx" name="idx" placeholder="idx" maxlength="20" value="${menu.idx}" readonly>

            <input type="text" id="memID" name="memID" placeholder="회원아이디" maxlength="20" value="${menu.memID}" readonly>

            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목" maxlength="10" value="${menu.title}" readonly>

            <label for="title">내용</label>
            <input type="text" id="content" name="content" placeholder="내용" maxlength="30" value="${menu.content}" readonly>

            <label for="title">작성자</label>
            <input type="text" id="writer" name="writer" placeholder="작성자" maxlength="10" value="${menu.writer}" readonly>

            <div id="buttonContainer">
                <c:if test="${MANAGER == true}">
                    <button type="button" id="buttonUpdate">수정</button>
                    <button type="button" id="buttonDelete">삭제</button>
                </c:if>
            </div>

        </div>
    </div>
</form>


<%@ include file="../common/footer.jsp"%>


<script src="${pageContext.request.contextPath}/resources/js/noticeCheck/script.js"></script>

</body>
</html>
