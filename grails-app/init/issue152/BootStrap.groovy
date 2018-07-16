package issue152

import demo.Widget

class BootStrap {

    def init = { servletContext ->
        100.times { count ->
            new Widget(name: "Widget ${count}").save()
        }
    }
    def destroy = {
    }
}
