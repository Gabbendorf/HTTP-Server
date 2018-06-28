package router;

import controllers.*;
import controllers.fileSystem.FileSystem;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.Map;

public class Router {

    private final Map<String, Controller> controllers;
    private final Logger logger;

    public Router(Logger logger, FileSystem fileSystem) {
        this.controllers = new Configuration().setControllers(logger, fileSystem);
        this.logger = logger;
    }

    public HTTPResponse route(HTTPRequest request) {
        logger.log(request.getRequestLine());
        Controller controller = getController(request.getFirstPathSegment());
        return controller.respondTo(request);
    }

    private Controller getController(String requestPath) {
        return controllers.getOrDefault(requestPath, new NotFoundController());
    }
}
