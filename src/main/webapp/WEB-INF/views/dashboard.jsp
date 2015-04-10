<%@ include file="header.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="p" %>

	<section id="main">
	<div class="container">
		<h1 id="homeTitle"><c:out value="${ total }" /> computers found</h1>
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
					Add Computer
				</a>
				<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
					Edit
				</a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="delete" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<div>Page <c:out value="${ currentPage }" /> / <c:out value="${ totalPages }" /></div>
		<table class="table table-striped table-bordered tablesorter" id="dashboard">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;">
						<input type="checkbox" id="selectall" />
						<span style="vertical-align: top;">
							 - 
							<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
								<i class="fa fa-trash-o fa-lg"> </i>
							</a>
						</span>
					</th>
					<th>Computer name</th>
					<th>Introduced date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued date</th>
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
							<fmt:parseDate value="${ computer.introduced }" var="introducedDate" pattern="yyyy-MM-dd" />
	                		<fmt:formatDate value="${ introducedDate }" type="Date" dateStyle="short" pattern="dd/MM/yyyy" />
						</td>
						<td>
							<fmt:parseDate value="${ computer.discontinued }" var="discontinuedDate" pattern="yyyy-MM-dd" />
	                		<fmt:formatDate value="${ discontinuedDate }" type="Date" dateStyle="short" pattern="dd/MM/yyyy" />
						</td>
						<td>
							<c:out value="${ computer.companyName }" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>Page <c:out value="${ currentPage }" /> / <c:out value="${ totalPages }" /></div>
	</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<p:pages totalPages="${ totalPages }" page="${ page.page }" pageCount="${ maxPages }"
				 pageSize="${ page.size }" url="dashboard" previous="${ page.previous }"/>
	</footer>
	
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
	
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>

<%@ include file="footer.jsp" %>