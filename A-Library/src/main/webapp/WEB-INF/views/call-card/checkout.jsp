<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>		
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>CheckOut</h3>
						</div>
					</div>											
			<div class="clearfix"></div>
				<div class="panel panel-default">
					<div class="panel-body">				
					<c:if test="${callCard !=null}">
						<div class="x_panel" style="">
						    <div class="x_title">
						        <div class="clearfix"></div>
						    </div>
						    <div class="x_content">
						
						        <div class="container">
							       	 <div class="row">					
							               <div class='col-sm-3'>
							                    Mã số sinh viên
							                    <div class="form-group">
							                     	 <input type='text' class="form-control" value="${callCard.libaryCardDTO.readersDTO.mssv}" readonly="readonly" />
							                    </div>
							                </div>
							                <div class='col-sm-3'>
							                    Tên sinh viên
							                    <div class="form-group">
							                    	  <input type='text' class="form-control" value="${callCard.libaryCardDTO.readersDTO.name}"  readonly="readonly" />
							                    </div>
							                </div>
							                 <div class='col-sm-3'>
							                   Tên lớp
							                    <div class="form-group">
							                    	  <input type='text' class="form-control" value="${callCard.libaryCardDTO.readersDTO.nameClass}"  readonly="readonly" />
							                    </div>
							                </div>						                
							            </div>
						         	<c:if test="${callCard.cardDetailDTOs !=null}">
							         	<c:forEach items="${callCard.cardDetailDTOs}" var="card">
									         	<div class="row">					
									               <div class='col-sm-3'>
									                    Sách
									                    <div class="form-group">
									                      <input type='text' class="form-control" value="${card.productInfoDTO.name}" readonly="readonly"/>
									                    </div>
									                </div>
									                <div class='col-sm-3'>
									                    Bắt đầu 
									                    <div class="form-group">
									                         <input type='text' value='<fmt:formatDate pattern="dd-MM-yyyy" value="${callCard.dateIssue}"/>' class="form-control"  readonly="readonly"/>
									                    </div>
									                </div>
									                 <div class='col-sm-3'>
									                   Đến hạn
									                    <div class="form-group">									                    
									                         <input type='date' id="dueDate"  name="dueDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${card.dueDate}"/>' class="form-control"  />
									                    </div>
									                </div>
									                <div class='col-sm-3'>
									                   Tác Vụ
									                    <div class="form-group">									                    									                    	
									                  	  <input type="hidden" id="idProduct" value="${card.productInfoDTO.id}">
									                        <a href='<c:url value="/call-card-detail/session/remove/${card.productInfoDTO.id}"></c:url>'><button class="btn btn-danger" title="Xóa"><i class="glyphicon glyphicon-trash"></i></button></a>
															<button  class="btn btn-warning btnEdit" title="Cập nhật"><i class="glyphicon glyphicon-edit"></i></button>
									                    </div>
									                </div>						                
									            </div>
							         	</c:forEach>
						         	</c:if>
						        </div>
						    </div>
						</div>
					</c:if>
					</div>
					<div class="panel-footer">
						<a href='<c:url value="/call-card/save"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-ok-circle"></i> Đồng ý</button></a>
						<a href='<c:url value="/call-card/add/1"></c:url>'><button class="btn btn-success"><i class="glyphicon glyphicon-minus-sign"></i> Trở về</button></a>
					</div>
				</div>									
				</div>
			</div>
			
			<script type="text/javascript">
			///call-card-detail/session/edit/
			var btnEdit = document.getElementsByClassName("btnEdit");
			var dueDate = document.getElementsByClassName("dueDate");
			for(var i = 0 ; i < btnEdit.length ; i++){
				btnEdit[i].addEventListener("click",function(){
					var id = $(this).parent().find("#idProduct").val();				
					var dueDate = $(this).parent().parent().siblings().find("#dueDate").val();
					window.location.href="<c:url value='/call-card-detail/session/edit/' />"+id+"?dueDate="+dueDate;
				});
			}		
			</script>
			
			
			