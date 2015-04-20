<%@ include file="header.jsp" %>

	<spring:message code='label.computerName' var="computerNameString" />
	<spring:message code='label.introducedDate' var="introducedDateString" />
	<spring:message code='label.discontinuedDate' var="discontinuedDateString" />
	<spring:message code='label.button.edit' var="editString" />

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="label.title.addComputer" /></h1>
                    <form id="addForm" action="add" method="POST">
                        <fieldset>
                 		    <div class="form-group" id="errors"></div>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="label.computerName" /></label>
                                <input type="text" class="form-control" id="computerName"
                                		name="computerName" placeholder="${ computerNameString }">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="label.introducedDate" /></label>
                                <input type="date" class="form-control" id="introduced"
                                		name="introduced" placeholder="${ introducedDateString }"
                                		min="1970-01-02" max="2038-01-19">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="label.discontinuedDate" /></label>
                                <input type="date" class="form-control" id="discontinued"
                                		name="discontinued" placeholder="${ discontinuedDateString }">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="label.company" /></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<option value="0">--</option>
                                	<c:forEach var="company" items="${ companies }">
										<option value="${ company.id }">
											${ company.name }
										</option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <script src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>
    <script src="<c:url value="/resources/js/validation.js" />"></script>
    
<%@ include file="footer.jsp" %>