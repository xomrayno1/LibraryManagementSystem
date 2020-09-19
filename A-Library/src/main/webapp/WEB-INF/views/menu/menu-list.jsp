<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
		<div class="x_title">
			<h2>Menu</h2>

			<div class="clearfix"></div>
		</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/menu/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="url">Tên Url<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="url" cssClass="form-control"/>
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
		<a href='<c:url value="/menu/permission"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> Permission</button></a>
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th rowspan="2" class="column-title" style="border-left: 2px solid;">#</th>
                  			<th rowspan="2" class="column-title" style="border-left: 2px solid">Url</th>
                  			<th rowspan="2" class="column-title" style="border-left: 2px solid">Status</th>
                  			<th class="column-title  text-center" colspan="${roles.size()}" style="border-left: 2px solid" >Role</th>                              
                          </tr>
                          <tr>
                          	<c:forEach items="${roles}" var="role">
                          		<th class="column-title" style="border-left: 2px solid">${role.name}</th>
                          	</c:forEach>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${listProduct}" var="menu" varStatus="i"> 
                          	<tr>
                          	
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${menu.url} <input type="hidden" id="idStatus" value="${menu.id}"></td>
                            <c:choose>								                        	
                            	<c:when test="${menu.activeFlag == 1 }">
                            		<td><button data-target="#statusModal" data-toggle="modal" class="btn btn-round btn-danger btn-status">Disable</button></td>
                            	</c:when>
                            	<c:otherwise>
                            		<td><button data-target="#statusModal" data-toggle="modal" class="btn btn-round btn-primary btn-status">Enable</button></td>
                            	</c:otherwise>
                            	
                            </c:choose>             
                            <c:forEach items="${menu.mapAuth}" var="auth">
                            	<c:choose>
                            		<c:when test="${auth.value == 1}">
                            			<td class="text-center"><i class="fa fa-check" style="color: green"></i></td>
                            		</c:when>
                            		<c:otherwise>
                            			<td class="text-center"><i class="fa fa-times" style="color:red;"></i></td>
                            		</c:otherwise>
                            	</c:choose>
                            </c:forEach>	
                
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
	<jsp:include page="/WEB-INF/views/layout/paging.jsp"></jsp:include>


      </div>
		<div id="statusModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<p class="modal-title">Xóa</p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body">
						Bạn có chắc chắn muốn tắt không !
					</div>
					<c:url value="/menu/change-status" var="urlDelete" />
					<form action="${urlDelete}" method="post">
						<input type="hidden" id="idModalStatus" name="id">
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
		$("#searchForm").attr('action',"<c:url value='/menu/list/'/>"+page);
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
		$('#menulistId').parents("li").addClass('active').siblings().removeClass("active");
		$('#menulistId').addClass('current-page').siblings().removeClass("current-page");
		$('#menulistId').parents().show();
	});
	
	var btnStatus = document.getElementsByClassName('btn-status');
	for(var i = 0 ; i < btnStatus.length ;i++){
		btnStatus[i].addEventListener('click',function(){
			var idStatus =$(this).parents("tr").find("#idStatus").val();
			console.log(idStatus);
			$("#idModalStatus").val(idStatus);
		});
	}
	
</script>



