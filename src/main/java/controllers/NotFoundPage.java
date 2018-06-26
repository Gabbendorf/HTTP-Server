package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.NOT_FOUND;

public class NotFoundPage extends Controller {

    private HTTPResponse notFoundResponse = new HTTPResponse(NOT_FOUND);

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return notFoundResponse;
    }

    @Override
    public HTTPResponse put(HTTPRequest request) {
        return notFoundResponse;
    }

    @Override
    public HTTPResponse post(HTTPRequest request) {
        return notFoundResponse;
    }

    @Override
    public HTTPResponse head(HTTPRequest request) {
        return notFoundResponse;
    }

    @Override
    public HTTPResponse options(HTTPRequest request) {
        return notFoundResponse;
    }

    @Override
    public HTTPResponse patch(HTTPRequest request) {
        return notFoundResponse;
    }
}
