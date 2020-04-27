
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title"><h2>Q&A 답변	</h2></label>

    <div class="qna-table">
      <table class="table table-striped table-hover">
        <tr>
          <th>제목</th>
          <td>
            <label id="qna_Title" name="qna_Title"> ${ list.qna_Title }</label>
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <label id="qna_Content" name="qna_Content">
              ${ list.qna_Content }</label
            >
          </td>
        </tr>
        <tr>
          <th>작성자</th>
          <td>
            <label id="qna_Writer" name="qna_Writer">
              ${ list.qna_Writer }</label
            >
          </td>
        </tr>
      </table>
    </div>

    
        <form id="qna_reply" name="qna_reply" action="qna_board" method="post">
   
    <div class="x_content">

					
					<div class="qna-table">


						<table class="table">
							<tr>
								<td>제목</td>
								<td><input id="title" name="title" class="form-control" /></td>
							</tr>
							<tr>
								<td>내용</td>
								<td>
									<textarea class="form-control col-sm-12" rows="8"></textarea>
								</td>
							</tr>
						</table>

					</div>
				</div>

      
				<div class="qna_bottom">
					<button type="button" class="btn btn-outline-success"
						onclick="check_blanck()">  답변 등록</button>
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href='qna_list'">목록</button>
				</div>
      
      
    </form>
    
    
    
    
  </div>
</div>

	