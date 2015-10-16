<%@ page contentType="text/html; charset=utf-8" %>

<link href="css/bootstrap.min.css" rel="stylesheet">

<div class="container">
	<div class="row" align="center">
		<div class="col-lg-12 text-center v-center">
          <h2>멀티 게시판</h2>
          <p class="lead">Bootstrap 3와 MVC를 이용한 멀티 게시판</p>
          <br> 
          <form class="col-lg-12">
            <div class="input-group input-group-sm col-sm-offset-4 col-sm-4">
              <input type="text" class="center-block form-control input-lg" title="검색어 입력." placeholder="검색어를 입력하시오">
              <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="button">OK</button></span>
            </div>
          </form>
        </div>
		<div class="col-md-12 column">
			<table class="table table-hover table-condensed table-striped">
				<thead>
					<tr>
						<th class="col-sm-1"> 번호 </th> <th class="col-sm-3"> 제목 </th> <th class="col-sm-2">글쓴이 </th> <th class="col-sm-2"> 날짜 </th> <th class="col-sm-1"> 조회수 </th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							1
						</td>
						<td>
							TB - Monthly
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							Default
						</td>
						<td>
							Default
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<ul class="pagination">
				<li>
					<a href="#">Prev</a>
				</li>
				<li>
					<a href="#">1</a>
				</li>
				<li>
					<a href="#">2</a>
				</li>
				<li>
					<a href="#">3</a>
				</li>
				<li>
					<a href="#">4</a>
				</li>
				<li>
					<a href="#">5</a>
				</li>
				<li>
					<a href="#">Next</a>
				</li>
			</ul>
	</div>
</div>