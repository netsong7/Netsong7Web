<%@ page contentType="text/html; charset=utf-8" %>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://netsong7.com">netsong7.com</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="#">About</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<form class="nav navbar-form pull-right">
				<a href="#" data-toggle="modal" data-target="#squarespaceModal"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>로그 인</a> 
			</form>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>

<!-- 로그인 모달창 -->
<div class="modal fade alert" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">x</span><span class="sr-only">Close</span></button>
            <h3 class="modal-title" id="lineModalLabel">Modal Login Window</h3>
        </div>
        <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="exampleInputEmail1">아이디</label>
                <input type="email" class="form-control" id="user-id" placeholder="Enter ID">
              </div>
              
              <div class="form-group">
                <label for="exampleInputPassword1">패스워드</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password">
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox"> Remember login
                </label>
              </div>
              <button type="submit" class="btn btn-primary center-block">Login</button>
            </form>
        </div>
    </div>
  </div>
</div>