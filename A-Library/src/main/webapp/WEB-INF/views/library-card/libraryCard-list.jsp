<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h4>Thẻ thư viện</h4>
			</div>
		</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/library-card/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align" for="mssv">Mã số sinh viên<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="readersDTO.mssv" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="readersDTO.name" cssClass="form-control"/>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> Search</button>												
												<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>												
											</div>
										</div>

									</form:form>
								</div>
							</div>
						</div>
					</div>
		
	<div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>
                            <th class="column-title">Tên</th>
                            <th class="column-title">Mã số sinh viên</th>
                            <th class="column-title">Ngày bắt đầu</th>
                          	<th class="column-title">Ngày hết hạn</th>
                          	<th class="column-title">Phiếu mượn</th>
                            <th class="column-title no-link last text-center" colspan="4" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${listProduct}" var="card" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${card.id}</td>                           
                        	<td>${card.readersDTO.name}</td> 
                        	<td>${card.readersDTO.mssv}</td>                         	
                        	<td>${card.startDay}</td>    
                        	<td>${card.endDay}</td> 
                        	<td><a href='<c:url value="/call-card/session/add/${card.id}"></c:url>'><button class="btn btn-info"><i class="glyphicon glyphicon-file"></i></button></a></td>                                                           	
                            <td colspan="4" class="last text-center">
                            	<input type="hidden" id="idProduct" value="${card.id}">
	                            <a href='<c:url value="/library-card/view/${card.id}"></c:url>' class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i></a> 
	                            <a href='<c:url value="/library-card/edit/${card.id}"></c:url>' class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
	                            <a data-toggle="modal" data-target="#deleteModal" class="btn btn-danger btn-delete"><i class="glyphicon glyphicon-trash"></i></a>
                            </td>                  
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
	<jsp:include page="/WEB-INF/views/layout/paging.jsp"></jsp:include>


      </div>
		<div id="deleteModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<p class="modal-title">Xóa</p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body">
						Bạn có chắc chắn muốn xóa không !
					</div>
					<c:url value="/library-card/delete" var="urlDelete" />
					<form action="${urlDelete}" method="post">
						<input type="hidden" id="idModalDelete" name="id">
						<div class="modal-footer">
							<button type="submit" class="btn btn-default" >Có</button>
							<button  class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/library-card/list/'/>"+page);
		$("#searchForm").submit();
	}
	
	$(document).ready(function(){
		var msgError = '${msgError}';
		var msgSuccess ='${msgSuccess}';
		if(msgError){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgError,
		        type: 'error',
		        styling: 'bootstrap3'
		        
		    });	
		}
		if(msgSuccess){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgSuccess,
		        type: 'success',
		        styling: 'bootstrap3'
		    });	
		}
	});
	
	$(document).ready(function(){
		$('#library-cardlistId').parents("li").addClass('active').siblings().removeClass("active");
		$('#library-cardlistId').addClass('current-page').siblings().removeClass("current-page");
		$('#library-cardlistId').parents().show();
	});
	
	var btnDelete = document.getElementsByClassName('btn-delete');
	for(var i = 0 ; i < btnDelete.length ;i++){
		btnDelete[i].addEventListener('click',function(){
			var idProduct =	$(this).parent().find("#idProduct").val();
			$("#idModalDelete").val(idProduct);
		});
	}
	
</script>



