<%@ page import="com.github.jwickard.cloudbitgrails.Flavor" %>



<div class="form-group fieldcontain ${hasErrors(bean: flavorInstance, field: 'name', 'error')} required">
	<label for="name" class="control-label"><g:message code="flavor.name.label" default="Name" /><span class="required-indicator">*</span></label>
	<g:textField name="name" required="" class="form-control" value="${flavorInstance?.name}"/>

</div>

