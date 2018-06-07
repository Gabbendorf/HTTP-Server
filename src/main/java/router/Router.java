package router;

import request.HTTPRequest;
import response.HTTPResponse;

public class Router {

    private final MethodFactory methodFactory;

    public Router() {
        this.methodFactory = new MethodFactory();
    }

    public HTTPResponse route(HTTPRequest request) {
        String method = request.getMethod();
        HTTPMethod httpMethod = methodFactory.create(method);
        return httpMethod.dispatch(request);
    }
}
