package router;

import controllers.*;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.Map;

public class Router {

    private final Map<String, Controller> controllers;

    public Router() {
        this.controllers = new Configuration().setControllers();
    }

    public HTTPResponse route(HTTPRequest request) {
        Controller controller = getController(request.getPath());
        return controller.respondTo(request);
    }

    private Controller getController(String requestPath) {
        return controllers.getOrDefault(requestPath, new NotFoundPage());
    }
}
