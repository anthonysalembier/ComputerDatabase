<%@ include file="header.jsp" %>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: <c:out value="${ computer.id }" />
                    </div>
                    <h1>Edit Computer</h1>
                    <form action="edit" method="POST">
                        <input name="computerId" type="hidden" value="${ computer.id }"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName"
                                		name="computerName" placeholder="Computer name"
                                		value="${ computer.name }">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced"
                                		name="introduced" placeholder="Introduced date"
                                		value="${ computer.introduced }">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued"
                              			name="discontinued"	placeholder="Discontinued date"
                              			value="${ computer.discontinued }">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
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
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
<%@ include file="footer.jsp" %>