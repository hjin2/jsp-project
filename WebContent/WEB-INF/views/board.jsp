<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<style>
.writerWrap {
	margin: 0 auto;
}

.tit {
	text-align: center;
}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="tit">게시물 작성</h1>


	<div class="col-md-12">
		<div class="col-md-8 writerWrap">
			<form action="/board" method="post" id="frm" onsubmit="return validChk();">
				<div class="form-group">
					<label for="title">제목</label> <input type="text" name="title"
						class="form-control" id="title">
				</div>
				<div class="form-group">
					<label for="title">작성자</label> <input type="text" name="writer"
						class="form-control" id="writer" value="${loginUser }" readonly="readonly" }>
				</div>
				<div class="form-group">
					<label for="title">내용</label>
					<textarea name="content" class="form-control" id="content"></textarea>
				</div>
				<button class="btn btn-primary">작성</button>
			</form>
		</div>
	</div>
<script>
	function validChk(){
		if(frm.title.value==""){
			alert('제목을 입력해주세요.');
			frm.title.focus();
			return false;
		}
		if(frm.content.value==''){
			alert('내용을 입력해주세요')
			frm.content.focus();
			return false;
		}
	}
	<!-- 클라이언트에서 굳이 하는 이유,,서버와 클라이언트의 차이는
	그냥 내 컴 개발해놓은 소스들을 그대로 리눅스 환경에 배포해놓는다...
	배포해두면 지금은 로컬host 8080 이지만 이게 dns서버를 거쳐서 www.naver.com 형식으로
	바뀐다...리눅스서버에 담으면 앞부분이 도메인 이름으로 바뀐다...
	그러면 이클립스소스 있는ㄱ 서버컴퓨터. 서버 돌릴때 리눅스 서버에 올리면
	언니가 서버에 와서 글을씀 ..엄마가 와서 글을 씀..서버에 ...서버기준 언니컴이 ,엄마컴이 클라이언트가 됨
	결론 : 서버단에 보내기전에 클라이언트에서 빈게 없는지 확인 , 과부하 없도록!!
	클라이언트를 잘 개발해야 서버를 효율적으로 운영할 수 있다.-->
</script>


</body>
</html>