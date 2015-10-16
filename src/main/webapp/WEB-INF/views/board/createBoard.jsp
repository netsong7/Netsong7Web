<%@page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Create Board</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<form method="post" action="board.app?cmd=CREATE_BOARD">
				<div class="col-lg-7">
					<div class="input-group">
					  	<span class="input-group-addon">게시판 이름 입력</span>
					  	<input type="text" class="form-control" aria-label="생성할 게시판 이름을 입력하시오.">
					  	<span class="input-group-btn">
	        				<button class="btn btn-default" type="submit">게시판 생성</button>
	      				</span>
					</div>
				</div>
			</form>
		</div>
		
		<div class="row">
			<h2>게시판 목록</h2>
		</div>
	</div>
</body>
</html>