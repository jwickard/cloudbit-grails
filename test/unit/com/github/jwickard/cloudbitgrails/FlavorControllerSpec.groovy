package com.github.jwickard.cloudbitgrails



import grails.test.mixin.*
import spock.lang.*

@TestFor(FlavorController)
@Mock(Flavor)
class FlavorControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.flavorInstanceList
            model.flavorInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.flavor != null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def flavor = new Flavor()
            flavor.validate()
            controller.save(flavor)

        then:"The create view is rendered again with the correct model"
            model.flavor != null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            flavor = new Flavor(params)

            controller.save(flavor)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/flavor/index'
            controller.flash.message != null
            Flavor.count() == 1
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def flavor = new Flavor(params)
            controller.edit(flavor)

        then:"A model is populated containing the domain instance"
            model.flavor == flavor
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/flavor/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def flavor = new Flavor()
            flavor.validate()
            controller.update(flavor)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.flavorInstance == flavor

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            flavor = new Flavor(params).save(flush: true)
            controller.update(flavor)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/flavor/show/$flavor.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/flavor/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def flavor = new Flavor(params).save(flush: true)

        then:"It exists"
            Flavor.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(flavor)

        then:"The instance is deleted"
            Flavor.count() == 0
            response.redirectedUrl == '/flavor/index'
            flash.message != null
    }
}
