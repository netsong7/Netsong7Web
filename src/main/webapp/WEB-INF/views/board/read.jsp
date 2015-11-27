<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Write Board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

<div class="page-header">
    <h1>글읽기 <small>이곳은 게시판의 글을 읽는 곳입니다.</small></h1>
</div>

<!-- Contact Form - START -->
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="well well-sm">
                <form class="form-horizontal" method="post">
                    <fieldset>
                        <legend class="text-center header">글을 읽어 주세요</legend>

                        <div class="form-group">
                            <span class="col-md-2 text-center">제목</span>
                            <div class="col-md-10">
                                ${board["wr_title"]}
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-md-2 text-center">글쓴이</span>
                            <div class="col-md-10">
                                ${board["wr_writer"]}
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-2 text-center">이메일</span>
                            <div class="col-md-10">
                                ${board["wr_email"]}
                            </div>
                        </div>

                        <div class="form-group">
                            <span class="col-md-2 text-center">홈페이지</span>
                            <div class="col-md-8">
                                ${board["wr_home"]}
                            </div>
                        </div>
                        
                        <c:if test='${master["board_upload"] == "y"}'>
                        <div class="form-group">
                            <span class="col-md-2 text-center">업로드</span>
                            <div class="col-md-8">
                                <input id="wr_file" name="wr_file" type="file" placeholder="Upload"">
                            </div>
                        </div>
						</c:if>
						
                        <div class="form-group">
                            <span class="col-md-2 text-center">내용</span>
                            <div class="col-md-10">
                                ${board["wr_contents"]}
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="button" class="btn btn-primary">수정</button>
                                <button type="button" class="btn btn-primary">답변</button>
                                <button type="button" class="btn btn-primary">댓글</button>
                                <button type="button" class="btn btn-primary">삭제</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<style>
    .header {
        color: #36A0FF;
        font-size: 27px;
        padding: 10px;
    }

    .bigicon {
        font-size: 35px;
        color: #36A0FF;
    }
</style>

<!-- Contact Form - END -->

</div>

</body>
</html>