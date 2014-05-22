
<%@ page import="com.github.jwickard.cloudbitgrails.IceCreamEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'iceCreamEntry.label', default: 'IceCreamEntry')}" />
		<title><g:message code="icecream.list.header" /></title>
	</head>
	<body>
		<div id="list-iceCreamEntry" class="content scaffold-list" role="main">
			<h1><g:message code="icecream.list.header" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered">
			<thead>
					<tr>
					
						<th><g:message code="iceCreamEntry.flavor.label" default="Flavor" /></th>
					
						<g:sortableColumn property="scoops" title="${message(code: 'iceCreamEntry.scoops.label', default: 'Scoops')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${iceCreamEntryInstanceList}" status="i" var="iceCreamEntryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${iceCreamEntryInstance.id}">${fieldValue(bean: iceCreamEntryInstance, field: "flavor.name")}</g:link></td>
					
						<td>${fieldValue(bean: iceCreamEntryInstance, field: "scoops")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${iceCreamEntryInstanceCount ?: 0}" />
			</div>
            <div><g:link class="btn btn-primary" action="create">Create Entry</g:link></div>
		</div>
	</body>
</html>
