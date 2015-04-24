<%@ include file="header.jsp" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="th"%>
	
	<spring:message code='label.computerName' var="computerNameString" />
	<spring:message code='label.introducedDate' var="introducedDateString" />
	<spring:message code='label.discontinuedDate' var="discontinuedDateString" />
	<spring:message code='label.button.edit' var="editString" />

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${ computer.id }" />
                    </div>
                    <h1><spring:message code="label.title.editComputer" /></h1>
                    <th:form id="editForm" action="edit" method="POST" modelAttribute="computeDTO">
                        <input id="id" name="id" type="hidden" value="${ computer.id }"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="name"><spring:message code="label.computerName" /></label>
                                <div id="error-name"></div>
                                <input type="text" class="form-control" id="name" name="name" placeholder="${ computerNameString }" value="${ computer.name }">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="label.introducedDate" /></label>
                            	<div id="error-introduced"></div>
                                <input type="text" class="form-control datepicker" id="datepicker-introduced"
                                		name="introduced" placeholder="${ introducedDateString }" value="${ computer.introduced }" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="label.discontinuedDate" /></label>
                            	<div id="error-discontinued"></div>
                                <input type="text" class="form-control datepicker" id="datepicker-discontinued"
                                		name="discontinued" placeholder="${ discontinuedDateString }" value="${ computer.discontinued }" />
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.company" /></label>
                                <select class="form-control" id="companyId" name="companyId">
                                	<option value="0">--</option>
                                	<c:forEach var="company" items="${ companies }">
                                		<c:choose>
	                                		<c:when test="${ company.id == computer.companyId }">
		                                		<option value="${ company.id }" selected>
		                                			${ company.name }
		                                		</option>
	                                		</c:when>
	                                		<c:otherwise>
	                                			<option value="${ company.id }">
		                                			${ company.name }
		                                		</option>
	                                		</c:otherwise>
                                		</c:choose>
                                	</c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="${ editString }" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default"><spring:message code="label.button.cancel" /></a>
                        </div>
                    </th:form>
                </div>
            </div>
        </div>
    </section>
    
    <script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
    <script src="<c:url value="/resources/js/validation.js" />"></script>
    <script src="<c:url value="/resources/js/edition.js" />"></script>
    
<%@ include file="footer.jsp" %>