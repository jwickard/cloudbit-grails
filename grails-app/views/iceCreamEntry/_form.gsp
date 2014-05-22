<%@ page import="com.github.jwickard.cloudbitgrails.IceCreamEntry" %>



<div class="fieldcontain ${hasErrors(bean: iceCreamEntryInstance, field: 'flavor', 'error')} required">
	<label for="flavor">
		<g:message code="iceCreamEntry.flavor.label" default="Flavor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="flavor" name="flavor.id" from="${com.github.jwickard.cloudbitgrails.Flavor.list()}" optionKey="id" optionValue="name" required="" value="${iceCreamEntryInstance?.flavor?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: iceCreamEntryInstance, field: 'scoops', 'error')} required">
	<label for="scoops">
		<g:message code="iceCreamEntry.scoops.label" default="Scoops" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="scoops" type="number" value="${iceCreamEntryInstance.scoops}" required=""/>

</div>

