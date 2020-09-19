<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Tạo phiếu mượn</h4>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">				
								<div class="x_content">
									<div class="col-md-8 col-sm-8">										
											<form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left"  method="POST">
												<div class="item form-group">
													<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên sách <span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 ">
														<form:input path="productInfoDTO.name" class="form-control "/>														
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
									<div class="col-sm-4 col-md-4">
									<a href='<c:url value="/library-card/list/1"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> Phiếu mượn</button></a>
										<c:if test="${callCard !=null}">
												<div class="panel panel-primary">
													<div class="panel-heading clearfix">
														<p class="pull-left">Mã Sinh Viên : ${callCard.libaryCardDTO.readersDTO.mssv}</p>
														<a href='<c:url value="/call-card/session/remove"></c:url>' class="btn btn-default pull-right "><i class="glyphicon glyphicon-remove"></i></a>
													</div>
													<div class="panel-body">
														<ul	class="list-group">
															<c:forEach items="${callCard.cardDetailDTOs}" var="detail">
																<li class="list-group-item">${detail.productInfoDTO.name}</li>
															</c:forEach>
														</ul>
													</div>
													<div class="panel-footer">
														<a href='<c:url value="/call-card/check-out"></c:url>'><button class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Đồng Ý</button></a>
														<a href='<c:url value="/call-card/session/reset"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-refresh"></i> Reset</button></a>													
													</div>
												</div>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>	
 <div class="col-md-12 col-sm-12  ">
                <div class="x_panel">
                  <div class="x_content">
                    <div class="table-responsive">
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">                          
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>                         
                            <th class="column-title">Tên</th>
                            <th class="column-title">Ảnh</th>
                            <th class="column-title">Tác giả</th>
                            <th class="column-title">Số lượng</th>
                            <th class="column-title no-link last text-center" colspan="3" ><span class="nobr">Chọn</span> </th> 
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${listProduct}" var="product" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${product.id}</td>                         
                        	<td>${product.productInfoDTO.name}</td>
                        	<td><img alt="" src='<c:url value="/resources/${product.productInfoDTO.imgUrl}"></c:url>' width="100px;" height="100px;"></td>
                        	<td>${product.productInfoDTO.authorDTO.name}</td>
                        	<td>${product.quantity}</td>   
                        	<td><a href='<c:url value="/callcard-detail/session/add/${product.id}"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-check"></i></button></a></td>                     	                 
                          	</tr>
                          </c:forEach>                         
                        </tbody>
                      </table>
                      <jsp:include page="/WEB-INF/views/layout/paging.jsp"></jsp:include>
                    </div>
							
						
                  </div>
                </div>
              </div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/call-card/add/'/>"+page);
		$("#searchForm").submit();
	}
	$(document).ready(function(){
		$("#issuelistId").parents("li").addClass("active").siblings().removeClass("active");
		$("#issuelistId").addClass("current-page").siblings().removeClass("current-page");
		$("#issuelistId").parents().show();
	});
	
	
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
</script>



