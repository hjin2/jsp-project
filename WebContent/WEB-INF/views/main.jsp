<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
	tr:hover{background-color: #ddd; cursor: pointer}


</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- java standard tag library -->
   <div style="text-align: center;">
      <h1>메인 페이지</h1>
   		<c:if test="${loginUser != null}">
   		<h3>${loginUser }님 환영합니다.</h3>
   		</c:if>
   		
   		<c:if test="${loginUser == null}">
   		<a href="/join">회원가입</a>
   		<a href="/login">로그인</a>
   		</c:if>
   		<c:if test="${loginUser != null}">
   		<a href="/logout">로그아웃</a>
   		</c:if>
   		
      
      <a href="/board">게시물 작성</a>
   </div>
   <div class="col-md-12">
      <div class="col-md-8" style="margin:0 auto;">
         <table class="table">
            <thead class="thead-dark">
               <tr>
                  <th scope="col">NO</th>
                  <th scope="col">제목</th>
                  <th scope="col">작성자</th>
                  <th scope="col">조회수</th>
                  <th scope="col">작성일자</th>
               </tr>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="dto">
               <tr onclick="goBoardDetail(${dto.seq})">
                  <th scope="row">${dto.seq }</th>
                  <td>${dto.title }</td>
                  <td>${dto.writer }</td>
                  <td>${dto.viewCnt }</td><!-- 조회수 -->
                  <td>${dto.regDt }</td>
               </tr>
            
            </c:forEach>
            
            </tbody>
         </table>
      </div>
   </div>

<script>
function goBoardDetail(seq){
	location.href = "/board/detail?seq="+seq;
}
</script>


</body>
</html>