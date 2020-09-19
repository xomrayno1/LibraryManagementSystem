<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
				<div class="title_left">
					<h4>Phiếu Xuất</h4>
				</div>
				<div class="clearfix"></div>
		</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/goods-issue/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="fromDate">Từ  <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input type="date" path="fromDate" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="toDate">Đến  <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input type="date" path="toDate" cssClass="form-control"/>
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
		<a href='<c:url value="/goods-issue/add"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>Thêm</button></a>
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>
                            <th class="column-title">Tên sản phẩm</th>
                            <th class="column-title">Số lượng</th>
                            <th class="column-title">Thời gian</th>
                            <th class="column-title no-link last text-center" colspan="3" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${listProduct}" var="product" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${product.id}</td>
                            <td>${product.productInfoDTO.name}</td>
                            <td>${product.quantity}</td>   
                            <td>${product.createDate}</td>                  	
                            <td colspan="3" class="last text-center">
	                            <a href='<c:url value="/goods-issue/view/${product.id}"></c:url>' class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i></a> 
	                            <a href='<c:url value="/goods-issue/edit/${product.id}"></c:url>' class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a> 
                            </td>                  
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
	<jsp:include page="/WEB-INF/views/layout/paging.jsp"></jsp:include>


      </div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/goods-issue/list/'/>"+page);
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
		$('#goods-issuelistId').parents("li").addClass('active').siblings().removeClass("active");
		$('#goods-issuelistId').addClass('current-page').siblings().removeClass("current-page");
		$('#goods-issuelistId').parents().show();
	});
	
	var btnDelete = document.getElementsByClassName('btn-delete');
	for(var i = 0 ; i < btnDelete.length ;i++){
		btnDelete[i].addEventListener('click',function(){
			var idProduct =	$(this).parent().find("#idProduct").val();
			$("#idModalDelete").val(idProduct);
		});
	}
	
</script>



