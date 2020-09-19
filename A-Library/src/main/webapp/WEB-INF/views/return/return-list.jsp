<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Danh sách trả</h4>
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
									<c:url value="/return/list/1" var="searchUrl"/>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for=mssv>Mã số sinh viên<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="libraryCardDTO.readersDTO.mssv" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for=fromDate>Từ <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input type="date" path="fromDate" cssClass="form-control"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for=toDate>Đến <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input type="date"  path="toDate" cssClass="form-control"/>
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
                             <th class="column-title">Mã sinh viên</th>
                            <th class="column-title">Tên sách</th>
                            <th class="column-title">Ngày quy định</th>
                            <th class="column-title">Ngày Trả</th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${listProduct}" var="retu" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${retu.id}</td>                           
                        	<td>${retu.libraryCardDTO.readersDTO.mssv}</td> 
                        	<td>${retu.productInfoDTO.name}</td> 
                        	<td>${retu.dueDate}</td>   
                        	<td>${retu.returnDate}</td>                                  	               
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
		$("#searchForm").attr('action',"<c:url value='/return/list/'/>"+page);
		$("#searchForm").submit();
	}
	
	$(document).ready(function(){
		$('#returnlistId').parents("li").addClass('active').siblings().removeClass("active");
		$('#returnlistId').addClass('current-page').siblings().removeClass("current-page");
		$('#returnlistId').parents().show();
	});
	
</script>



