<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
		
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
									<c:url value="/publisher/save" var="submitUrl"></c:url>
									<form:form servletRelativeAction="${submitUrl}" method="POST" modelAttribute="submitForm" cssClass="form-horizontal form-label-left">
									<form:hidden path="id"/>
									<form:hidden path="activeFlag"/>
									<form:hidden path="createDate"/>
									<form:hidden path="updateDate"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="name" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="code">Code <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="code" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="code" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="email">Email <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="email" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="email" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>	
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="phone">Phone <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="phone" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="phone" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="info">Thông tin người đại diện <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="info" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="info" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="info">Địa chỉ Liên hệ <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="address" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="address" cssClass="help-block"/> 
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
												<a href='<c:url value="/publisher/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
					$("#publisherlistId").parents("li").addClass("active").siblings().removeClass("active");
					$("#publisherlistId").addClass("current-page").siblings().removeClass("current-page");
					$("#publisherlistId").parents().show();
				});
			</script>
			
			
			
			