<%@ include file="header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>
	
	<section id="main">
	<div class="container">
		<h1 id="homeTitle"><c:out value="${ total }" /> <spring:message code="label.computersFound" /></h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="dashboard" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" value="${ search }" />
					<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="add">
					<spring:message code="label.button.addComputer" />
				</a>
				<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
					<spring:message code="label.button.delete" />
				</a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="delete" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="selection" value="">
	</form>
	
	<div id="confirm-delete" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		    <div class="modal-content">
				<%--<div class="modal-header">--%>
					<%----%>
				<%--</div>--%>
				<div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.modal.confirmDelete.title" /></h4>
		        	<spring:message code="label.modal.confirmDelete.content" />
				</div>
				<div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.modal.confirmDelete.cancel" /></button>
			        <button type="button" class="btn btn-danger" onclick="$.fn.deleteSelected();"><spring:message code="label.modal.confirmDelete.confirm" /></button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" style="margin-top: 10px;">
		<div><spring:message code="label.page" /> <c:out value="${ currentPage }" /> / <c:out value="${ totalPages }" /></div>
		<table class="table table-striped table-bordered tablesorter" id="dashboard">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;">
						<input type="checkbox" id="selectall" />
						<span style="vertical-align: top;">
							 - 
							<a id="deleteSelected" href="#" onclick="$.fn.askDelete()" >
								 <i class="fa fa-trash-o fa-lg"> </i>
							</a>
						</span>
					</th>
					<th><spring:message code="label.computerName" /></th>
					<th><spring:message code="label.introducedDate" /></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="label.discontinuedDate" /></th>
					<!-- Table header for Company -->
					<th>Company</th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">
				<c:forEach var="computer" items="${ computers }">
					<tr>
						<td class="editMode">
							<input type="checkbox" name="cb" class="cb" value="${ computer.id }">
						</td>
						<td>
							<a href="edit?id=${ computer.id }" onclick="">
								<c:out value="${ computer.name }" />
							</a>
						</td>
						<td>
							<c:out value="${ computer.introduced }" />
						</td>
						<td>
							<c:out value="${ computer.discontinued }"  />
						</td>
						<td>
							<c:out value="${ computer.companyName }" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div><spring:message code="label.page" /> <c:out value="${ currentPage }" /> / <c:out value="${ totalPages }" /></div>
	</div>
	</section>
	
	<footer class="navbar-fixed-bottom">
		<p:pages totalPages="${ totalPages }" page="${ page.pageNumber }" pageCount="${ maxPages }"
				 pageSize="${ page.pageSize }" url="dashboard"/>
	</footer>
	
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>
	
<%@ include file="footer.jsp" %>