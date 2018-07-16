package demo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class WidgetController {

    WidgetService widgetService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond widgetService.list(params), model:[widgetCount: widgetService.count()]
    }

    def show(Long id) {
        respond widgetService.get(id)
    }

    def save(Widget widget) {
        if (widget == null) {
            render status: NOT_FOUND
            return
        }

        try {
            widgetService.save(widget)
        } catch (ValidationException e) {
            respond widget.errors, view:'create'
            return
        }

        respond widget, [status: CREATED, view:"show"]
    }

    def update(Widget widget) {
        if (widget == null) {
            render status: NOT_FOUND
            return
        }

        try {
            widgetService.save(widget)
        } catch (ValidationException e) {
            respond widget.errors, view:'edit'
            return
        }

        respond widget, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        widgetService.delete(id)

        render status: NO_CONTENT
    }
}
