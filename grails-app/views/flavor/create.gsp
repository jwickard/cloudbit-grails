<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'flavor.label', default: 'Flavor')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="create-flavor" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
			<g:form url="[resource:flavorInstance, action:'save']" >
					<g:render template="form"/>
				<fieldset class="buttons">
					<g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                    <g:link controller="flavor" action="index" class="btn btn-danger">Cancel</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
