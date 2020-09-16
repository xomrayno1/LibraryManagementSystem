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
									<c:url value="/product-info/save" var="submitUrl"></c:url>
									<form:form servletRelativeAction="${submitUrl}"  enctype="multipart/form-data" method="POST" modelAttribute="submitForm" cssClass="form-horizontal form-label-left">
									<form:hidden path="id"/>
									<form:hidden path="activeFlag"/>
									<form:hidden path="createDate"/>
									<form:hidden path="updateDate"/>
									<form:hidden path="imgUrl"/>
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
										<c:if test="${!viewOnly}">
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="code">Hình ảnh <span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">
													<form:input path="multipartFile" type="file" cssClass="form-control" readonly="${viewOnly}"/>
													<div class="has-error">
														<form:errors  path="imgUrl" cssClass="help-block"/> 
													</div>
												</div>
											</div>
										</c:if>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="authorId">Tác giả <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="authorId" cssClass="form-control">
															<form:option value="0">---------Select---------</form:option>
															<c:forEach items="${listAuthor}" var="author">
																<form:option value="${author.id}">${author.name}</form:option>
															</c:forEach>
														</form:select>
														<div class="has-error">
															<form:errors  path="authorId" cssClass="help-block"/>
														</div>
													</c:when>
													<c:otherwise>
														<form:input path="authorDTO.name" cssClass="form-control" readonly="${viewOnly}"/>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="cateId">Thể Loại <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">													
														<form:select path="cateId" cssClass="form-control" >
															<form:option value="0">---------Select---------</form:option>
															<c:forEach items="${listCategory}" var="category">
																<form:option value="${category.id}">${category.name}</form:option>
															</c:forEach>
														</form:select>
														<div class="has-error">
															<form:errors  path="cateId" cssClass="help-block"/>
														</div>													
													</c:when>
													<c:otherwise>
														<form:input path="categoryDTO.name" cssClass="form-control" readonly="${viewOnly}"/>
													</c:otherwise>	
												</c:choose>	
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="publisherId">Nhà xuất bản <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="publisherId" cssClass="form-control" >
															<form:option value="0">---------Select---------</form:option>														
															<c:forEach items="${listPublisher}" var="publisher">
																<form:option  value="${publisher.id}">${publisher.name}</form:option>
															</c:forEach>
														</form:select>
														<div class="has-error">
															<form:errors  path="publisherId" cssClass="help-block"/>
														</div>
													</c:when>
													<c:otherwise>
														<form:input path="publisherDTO.name"  cssClass="form-control" readonly="${viewOnly}"/>
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
												<a href='<c:url value="/product-info/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
					$("#product-infolistId").parents("li").addClass("active").siblings().removeClass("active");
					$("#product-infolistId").addClass("current-page").siblings().removeClass("current-page");
					$("#product-infolistId").parents().show();
				});
				
				
			</script>
			
			
			
			