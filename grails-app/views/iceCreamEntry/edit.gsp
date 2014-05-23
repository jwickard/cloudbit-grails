<%@ page import="com.github.jwickard.cloudbitgrails.IceCreamEntry" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'iceCreamEntry.label', default: 'IceCreamEntry')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-iceCreamEntry" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${iceCreamEntryInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${iceCreamEntryInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:iceCreamEntryInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${iceCreamEntryInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    <g:link controller="iceCreamEntry" action="index" class="btn btn-danger">Cancel</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
