<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
.writerWrap {
   margin: 0 auto;
}

.tit {
   text-align: center;
}
</style>

</head>
<body>

<div class="col-md-12">
      <div class="col-md-8 writerWrap">
      <h1>회원가입 페이지</h1>
         <form action="/join" method="post" onsubmit="return validChk()" id="frm">
            <div class="form-group">
               <label for="id">아이디</label> <input type="text" name="id"
                  class="form-control" id="id">
            </div>
            <div class="form-group">
               <label for="pw">패스워드</label> <input type="password" name="pw"
                  class="form-control" id="pw">
            </div>
            
            <button class="btn btn-primary">회원가입</button>
            <a href="/login" role="button" class="btn btn-secondary">로그인 페이지</a>
         </form>
      </div>
   </div>
   
   <script>
         function validChk(){
            if(frm.id.value == ''){
               alert('아이디를 입력해주세요.')
               frm.id.focus()
               return false;
            }
            if(frm.pw.value == ''){
               alert('패스워드를 입력해주세요.')
               frm.pw.focus();
               return false;
            }
         }
   
   </script>

   
</body>
</html>