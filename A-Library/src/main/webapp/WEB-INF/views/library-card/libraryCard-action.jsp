<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>		
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>${title}</h3>
						</div>
					</div>
					
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/library-card/save" var="submitUrl"></c:url>
									<form:form servletRelativeAction="${submitUrl}"  enctype="multipart/form-data" method="POST" modelAttribute="submitForm" cssClass="form-horizontal form-label-left">
									<form:hidden path="id"/>
									<form:hidden path="activeFlag"/>
									<form:hidden path="createDate"/>
									<form:hidden path="updateDate"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="readersDTO.mssv">Mã số sinh viên <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input  path="readersDTO.mssv" cssClass="form-control" readonly="true"/>
												<div>
													<div class="has-error">
														<form:errors   path="readersDTO.mssv" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="startDay">Ngày bắt đầu <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												 <!-- <form:input type="date" path="startDay"  cssClass="form-control" readonly="${viewOnly}" /> -->
												
												<div class="input-group date" id='fromDatePicker'>
													<form:input path="startDay" class="form-control" readonly="${viewOnly}" />
													<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<div>
													<div class="has-error">
														<form:errors  path="startDay" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="endDay">Ngày kết thúc <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
											 <!-- <form:input  path="endDay" cssClass="form-control" readonly="${viewOnly}"/> -->
												<div class="input-group date" id='toDatePicker'>
													<form:input path="endDay" class="form-control"  readonly="${viewOnly}"/>
													<span class="input-group-addon"> <span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
												<div>
													<div class="has-error">
														<form:errors   path="endDay" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Mô tả thêm <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="description" cssClass="form-control" readonly="${viewOnly}"/>	
												<div>
													<div class="has-error">
														<form:errors  path="description" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</div>
										<div class="ln_solid"></div>										
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/library-card/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
												</div>
											</div>

									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(document).ready(function(){
					$("#library-cardlistId").parents("li").addClass("active").siblings().removeClass("active");
					$("#library-cardlistId").addClass("current-page").siblings().removeClass("current-page");
					$("#library-cardlistId").parents().show();
				});
				
				 $(document).ready(function(){
					 $('#fromDatePicker').datetimepicker({
						 format : 'YYYY-MM-DD HH:mm:ss'
					 });
					 $('#toDatePicker').datetimepicker({
						 format : 'YYYY-MM-DD HH:mm:ss'
					 })
				 });
			</script>
			
			
			
			