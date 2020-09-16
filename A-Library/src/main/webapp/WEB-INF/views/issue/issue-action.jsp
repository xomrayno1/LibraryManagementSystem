<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>		
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>View</h3>
						</div>
					</div>											
			<div class="clearfix"></div>
				<div class="panel panel-default">
					<div class="panel-body">				
						<div class="x_panel" style="">
						    <div class="x_title">
						        <div class="clearfix"></div>
						    </div>
						    <div class="x_content">						
						        <div class="container">						         	
							         <c:forEach items="${callCard.cardDetailDTOs}" var="card">
									         	<div class="row">					
									               <div class='col-sm-3'>
									                    Sách
									                    <div class="form-group">
									                      <input type='text' class="form-control" value="${card.productInfoDTO.name}" readonly="readonly"/>
									                    </div>
									                </div>
									                <div class='col-sm-2'>
									                    Bắt đầu 
									                    <div class="form-group">
									                         <input type='text' value='<fmt:formatDate pattern="dd-MM-yyyy" value="${callCard.dateIssue}"/>' class="form-control"  readonly="readonly"/>
									                    </div>
									                </div>
									                 <div class='col-sm-2'>
									                   Đến hạn
									                    <div class="form-group">									                    
									                         <input type='date' id="dueDate"  name="dueDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${card.dueDate}"/>' class="form-control"  />
									                    </div>
									                </div>
									                <div class='col-sm-2'>
									                   Tình trạng
									                    <div class="form-group">
									                    	<c:if test="${card.status == 3}">
									                    		<input value='Đang mượn' class="form-control" readonly="readonly" />
									                    	</c:if>
									                    	<c:if test="${card.status == 4}">
									                    		<input  value='Đã trả' class="form-control" readonly="readonly" />
									                    	</c:if>									                    									                         
									                    </div>
									                </div>
									                <div class='col-sm-3'>
									                   Tác Vụ
									                    <div class="form-group">									                    									                    	
									                  	  	<input type="hidden" id="idProduct" value="${card.productInfoDTO.id}">									                  	  
									                  	  	<c:if test="${card.status == 3}">
									                  	  		<a href='<c:url value="/issue/return/${card.id}"></c:url>'><button  class="btn btn-success" title="Trả"><i class="glyphicon glyphicon-ok"></i></button></a>
									                  	  		<button  class="btn btn-warning btnEdit" title="Gia hạn"><i class="glyphicon glyphicon-edit"></i></button>
									                  	  	</c:if>
								
									                    </div>
									                </div>						                
									            </div>
							         </c:forEach>
						        </div>
						    </div>
						</div>
					</div>
					<div class="panel-footer">
						<a href='<c:url value="/issue/list/1"></c:url>'><button class="btn btn-success"><i class="glyphicon glyphicon-minus-sign"></i> Trở về</button></a>
					</div>
				</div>									
				</div>
			</div>
			
			<script type="text/javascript">
			$(document).ready(function(){
				$('#issuelistId').parents("li").addClass('active').siblings().removeClass("active");
				$('#issuelistId').addClass('current-page').siblings().removeClass("current-page");
				$('#issuelistId').parents().show();
			});
			/*
			var btnEdit = document.getElementsByClassName("btnEdit");
			var dueDate = document.getElementsByClassName("dueDate");
			for(var i = 0 ; i < btnEdit.length ; i++){
				btnEdit[i].addEventListener("click",function(){
					var id = $(this).parent().find("#idProduct").val();				
					var dueDate = $(this).parent().parent().siblings().find("#dueDate").val();
					window.location.href="<c:url value='/call-card-detail/session/edit/' />"+id+"?dueDate="+dueDate;
				});
			}	*/	 // chưa làm
			</script>
			
			
			