<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>               
                      <ul class="pagination">
                      	<c:forEach begin="1" end="${pageInfo.totalPages}" step="1" var="i">                     	
                      		<c:choose >
                      			<c:when test="${pageInfo.indexPage == i}">
                      				<li class="active"><a href="javascript:void(0)">${i}</a></li>
                      			</c:when>
                      			<c:otherwise>
                      				<li ><a href="javascript:void(0)" onclick="gotoPage(${i})">${i}</a></li>
                      			</c:otherwise>
                      		</c:choose>
                      	</c:forEach>
                      </ul>