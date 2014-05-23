package com.github.jwickard.cloudbitgrails



import grails.test.mixin.*
import spock.lang.*

@TestFor(IceCreamEntryController)
@Mock(IceCreamEntry)
class IceCreamEntryControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.iceCreamEntryInstanceList
            model.iceCreamEntryInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.iceCreamEntryInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def iceCreamEntry = new IceCreamEntry()
            iceCreamEntry.validate()
            controller.save(iceCreamEntry)

        then:"The create view is rendered again with the correct model"
            model.iceCreamEntryInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            iceCreamEntry = new IceCreamEntry(params)

            controller.save(iceCreamEntry)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/iceCreamEntry/index'
            controller.flash.message != null
            IceCreamEntry.count() == 1

    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def iceCreamEntry = new IceCreamEntry(params)
            controller.edit(iceCreamEntry)

        then:"A model is populated containing the domain instance"
            model.iceCreamEntryInstance == iceCreamEntry
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/iceCreamEntry/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def iceCreamEntry = new IceCreamEntry()
            iceCreamEntry.validate()
            controller.update(iceCreamEntry)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.iceCreamEntryInstance == iceCreamEntry

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            iceCreamEntry = new IceCreamEntry(params).save(flush: true)
            controller.update(iceCreamEntry)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/iceCreamEntry/show/$iceCreamEntry.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/iceCreamEntry/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def iceCreamEntry = new IceCreamEntry(params).save(flush: true)

        then:"It exists"
            IceCreamEntry.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(iceCreamEntry)

        then:"The instance is deleted"
            IceCreamEntry.count() == 0
            response.redirectedUrl == '/iceCreamEntry/index'
            flash.message != null
    }
}
