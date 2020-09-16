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
									<c:url value="/menu/update-permission" var="submitUrl"></c:url>
									<form:form servletRelativeAction="${submitUrl}" method="POST" modelAttribute="submitForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Role <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="idRole" cssClass="form-control">
													<form:options items="${mapRole}"/>
												</form:select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="idMenu">Menu <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<div>
													<form:select path="idMenu" cssClass="form-control">
														<form:options items="${mapMenu}"/>
													</form:select>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="email">Permission <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:radiobutton path="permission" value="1" /> Yes
												<form:radiobutton path="permission" value="0"/> No
											</div>
										</div>																				
										<div class="ln_solid"></div>										
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/menu/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
					$("#menulistId").parents("li").addClass("active").siblings().removeClass("active");
					$("#menulistId").addClass("current-page").siblings().removeClass("current-page");
					$("#menulistId").parents().show();
				});
			</script>
			
			
			
			