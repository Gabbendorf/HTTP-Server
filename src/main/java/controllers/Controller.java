package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.*;
import static controllers.HTTPMethod.create;

public class Controller {

    public HTTPResponse respondTo(HTTPRequest request) {
        HTTPMethod method = create(request.getMethod());
        switch (method) {
            case GET:
                return get(request);
            case PUT:
                return put(request);
            case HEAD:
                return head(request);
            case POST:
                return post(request);
            case OPTIONS:
                return options(request);
            case PATCH:
                return patch(request);
            case INVALID:
                return incorrect(request);
            default:
                return incorrect(request);
        }
    }

    protected HTTPResponse get(HTTPRequest request) {
        return notAllowedResponse();
    }

    protected HTTPResponse put(HTTPRequest request) {
        return notAllowedResponse();
    }

    protected HTTPResponse post(HTTPRequest request) {
        return notAllowedResponse();
    }

    protected HTTPResponse head(HTTPRequest request) {
        return notAllowedResponse();
    }

    protected HTTPResponse options(HTTPRequest request) {
        return notAllowedResponse();
    }

    protected HTTPResponse patch(HTTPRequest request) {
        return notAllowedResponse();
    }

    private HTTPResponse incorrect(HTTPRequest request) {
        return notAllowedResponse();
    }

    private HTTPResponse notAllowedResponse() {
        return new HTTPResponse(NOT_ALLOWED);
    }
}
