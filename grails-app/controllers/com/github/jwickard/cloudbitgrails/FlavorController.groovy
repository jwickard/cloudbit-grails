package com.github.jwickard.cloudbitgrails



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FlavorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Flavor.list(params), model:[flavorInstanceCount: Flavor.count()]
    }

    def show(Flavor flavorInstance) {
        respond flavorInstance
    }

    def create() {
        respond new Flavor(params)
    }

    @Transactional
    def save(Flavor flavorInstance) {
        if (flavorInstance == null) {
            notFound()
            return
        }

        if (flavorInstance.hasErrors()) {
            respond flavorInstance.errors, view:'create'
            return
        }

        flavorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'flavor.label', default: 'Flavor'), flavorInstance.id])
                redirect flavorInstance
            }
            '*' { respond flavorInstance, [status: CREATED] }
        }
    }

    def edit(Flavor flavorInstance) {
        respond flavorInstance
    }

    @Transactional
    def update(Flavor flavorInstance) {
        if (flavorInstance == null) {
            notFound()
            return
        }

        if (flavorInstance.hasErrors()) {
            respond flavorInstance.errors, view:'edit'
            return
        }

        flavorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Flavor.label', default: 'Flavor'), flavorInstance.id])
                redirect flavorInstance
            }
            '*'{ respond flavorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Flavor flavorInstance) {

        if (flavorInstance == null) {
            notFound()
            return
        }

        flavorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Flavor.label', default: 'Flavor'), flavorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'flavor.label', default: 'Flavor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
