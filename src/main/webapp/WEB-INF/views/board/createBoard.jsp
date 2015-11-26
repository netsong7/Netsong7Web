<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create Board</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script>
		// F5 새로고침 방지
		function noEvent() {
		     if (event.keyCode == 116) {
		         event.keyCode= 2;
		         return false;
		     }
		     else if(event.ctrlKey && (event.keyCode==78 || event.keyCode == 82)) {
		         return false;
		     }
		}
		document.onkeydown = noEvent;
	</script>
</head>
<body>
	<div class="container">
		<form method="post" action="board.manage?cmd=CREATE_BOARD">
			<div class="row">
				<div class="col-lg-7">
					<div class="input-group">
					  	<span class="input-group-addon">게시판 옵션 설정</span><br/>
					  	<label for="reply">답변 기능</label><input type="checkbox" class="form-control" name="chkOption" id="reply" value="reply"><br/>
					  	<label for="comment">댓글 기능</label><input type="checkbox" class="form-control" name="chkOption" id="comment" value="comment"><br/>
					  	<label for="upload">업로드 기능</label><input type="checkbox" class="form-control" name="chkOption" id="upload" value="upload"><br/>
					</div>
				</div>
			</div>
			<br/><br/>
			<div class="row">
				<div class="col-lg-7">
					<div class="input-group">
					  	<span class="input-group-addon">게시판 타이틀 입력(예:자유토론 게시판, QnA, 공지사항)</span>
					  	<input type="text" class="form-control" aria-label="생성할 게시판 타이틀을 입력하시오." name="boardTitle">
					</div>
					<div class="input-group">	
					  	<span class="input-group-addon">게시판 이름 입력(예:tblFreetalk, tblQnA, tblNotice)</span>
					  	<input type="text" class="form-control" aria-label="생성할 테이블명을 입력하시오." name="boardName">
					</div><br/>
					<div class="input-group">
					  	<span class="input-group-btn">
		        			<button class="btn btn-default" type="submit">게시판 생성</button>
		      			</span>
					</div>
				</div>
			</div>
		</form>
		<br/><br/>
		<div class="row">
			<h2>게시판 목록</h2>
			<ul>
				<c:forEach var="table" items="${requestScope.tableList}">
					<li><a href='board.manage?cmd=LIST_BOARD&boardNum=${table["board_num"]}'>${table["board_disp_name"]}(${table["board_tab_name"]} : ${table["board_create_date"]})</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>