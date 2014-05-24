
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
			<table class="table table-bordered table-striped">
			<thead>
					<tr>
					
						<th><g:message code="iceCreamEntry.flavor.label" default="Flavor" /></th>
					
						<g:sortableColumn property="scoops" title="${message(code: 'iceCreamEntry.scoops.label', default: 'Scoops')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${iceCreamEntryInstanceList}" status="i" var="iceCreamEntryInstance">
					<tr>
					
						<td><g:link action="edit" id="${iceCreamEntryInstance.id}">${fieldValue(bean: iceCreamEntryInstance, field: "flavor.name")}</g:link></td>
					
						<td>${fieldValue(bean: iceCreamEntryInstance, field: "scoops")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
            <div>
                <g:link class="btn btn-primary" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </div>
			<div class="pagination">
				<g:paginate total="${iceCreamEntryInstanceCount ?: 0}" />
			</div>

            <div class="well">
                <p>Ice Cream Entries created on this screen are pushed to FitBit via the FitBit JSON API.  You can view your log entries at: <a target="_blank" href="https://www.fitbit.com/foods/log">https://www.fitbit.com/foods/log</a></p>

                <p>Ice Cream is logged under 'Anytime' under Logged Foods:</p>

                <p><a target="_blank" href="https://www.fitbit.com/foods/log"><g:img dir="images" file="foodlog.png" /></a></p>
            </div>
		</div>
	</body>
</html>
