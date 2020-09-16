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
									<c:url value="/user/save" var="submitUrl"></c:url>
									<form:form servletRelativeAction="${submitUrl}" method="POST" modelAttribute="submitForm" cssClass="form-horizontal form-label-left">
									<form:hidden path="id"/>
									<form:hidden path="activeFlag"/>
									<form:hidden path="createDate"/>
									<form:hidden path="updateDate"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="username">Username <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${id > 0}">
														<form:input path="username" cssClass="form-control" readonly="true"/>		
													</c:when>
													<c:otherwise>
														<form:input path="username" cssClass="form-control" readonly="${viewOnly}"/>		
													</c:otherwise>
												</c:choose>
												<div>
													<div class="has-error">
														<form:errors  path="username" cssClass="help-block" />
													</div>
												</div>
											</div>
										</div>
										<c:if test="${!viewOnly}">
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="password">Password <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">										
													<form:input path="password" cssClass="form-control" readonly="${viewOnly}"/>																								
													<div>
														<div class="has-error">
															<form:errors  path="password" cssClass="help-block"/> 
														</div>
													</div>
												</div>
											</div>
										</c:if>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="fullName">Full Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="fullName" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="fullName" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>	
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="sdt">Phone <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="sdt" cssClass="form-control" readonly="${viewOnly}"/>																								<div>
													<div class="has-error">
														<form:errors  path="sdt" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="cmnd">CMND <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="cmnd" cssClass="form-control" readonly="${viewOnly}"/>
												<div>
													<div class="has-error">
														<form:errors  path="cmnd" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="info">Role <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="idRoles" cssClass="form-control">
															<c:forEach items="${roles}" var="role">
																<form:option value="${role.id}">${role.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>															
														<input class="form-control" value="${role.name}"  readonly="readonly">									
													</c:otherwise>
												</c:choose>
											</div>
										</div>											
										<div class="ln_solid"></div>										
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/user/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
					$("#userlistId").parents("li").addClass("active").siblings().removeClass("active");
					$("#userlistId").addClass("current-page").siblings().removeClass("current-page");
					$("#userlistId").parents().show();
				});
			</script>
			
			
			
			