package com.github.jwickard.cloudbitgrails

import grails.plugin.springsecurity.oauth.OAuthToken
import org.scribe.model.Token
import org.springframework.security.core.context.SecurityContextHolder

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IceCreamEntryController {

    def fitBitApiService
    def oauthService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IceCreamEntry.list(params), model:[iceCreamEntryInstanceCount: IceCreamEntry.count()]
    }

    def create() {
        respond new IceCreamEntry(params)
    }

    @Transactional
    def save(IceCreamEntry iceCreamEntryInstance) {

        Token fitbitAccessToken = session[oauthService.findSessionKeyForAccessToken('fitbit')]

        if (iceCreamEntryInstance == null) {
            notFound()
            return
        }

        if (iceCreamEntryInstance.hasErrors()) {
            respond iceCreamEntryInstance.errors, view:'create'
            return
        }

        iceCreamEntryInstance.save flush:true

        fitBitApiService.logIceCreamEntry(fitbitAccessToken, iceCreamEntryInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'iceCreamEntry.label', default: 'IceCreamEntry'), iceCreamEntryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*' { respond iceCreamEntryInstance, [status: CREATED] }
        }
    }

    def edit(IceCreamEntry iceCreamEntryInstance) {
        respond iceCreamEntryInstance
    }

    @Transactional
    def update(IceCreamEntry iceCreamEntryInstance) {
        if (iceCreamEntryInstance == null) {
            notFound()
            return
        }

        if (iceCreamEntryInstance.hasErrors()) {
            respond iceCreamEntryInstance.errors, view:'edit'
            return
        }

        iceCreamEntryInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'IceCreamEntry.label', default: 'IceCreamEntry'), iceCreamEntryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ respond iceCreamEntryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(IceCreamEntry iceCreamEntryInstance) {

        if (iceCreamEntryInstance == null) {
            notFound()
            return
        }

        iceCreamEntryInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'IceCreamEntry.label', default: 'IceCreamEntry'), iceCreamEntryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'iceCreamEntry.label', default: 'IceCreamEntry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
