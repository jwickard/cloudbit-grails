
<%@ page import="com.github.jwickard.cloudbitgrails.Flavor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'flavor.label', default: 'Flavor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-flavor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered">
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'flavor.name.label', default: 'Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${flavorInstanceList}" status="i" var="flavorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="edit" id="${flavorInstance.id}">${fieldValue(bean: flavorInstance, field: "name")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
            <div>
                <g:link class="btn btn-primary" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </div>
			<div class="pagination">
				<g:paginate total="${flavorInstanceCount ?: 0}" />
			</div>

		</div>
	</body>
</html>
