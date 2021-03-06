<%@ page import="com.github.jwickard.cloudbitgrails.Flavor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'flavor.label', default: 'Flavor')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-flavor" class="content scaffold-edit" role="main">
			<h1><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${flavorInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${flavorInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:flavorInstance, action:'update']" method="PUT" >
				<g:hiddenField name="version" value="${flavorInstance?.version}" />
					<g:render template="form"/>
					<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                <g:link controller="flavor" action="index" class="btn btn-danger">Cancel</g:link>
			</g:form>
		</div>
	</body>
</html>
