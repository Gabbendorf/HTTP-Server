package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.NOT_FOUND;

public class NotFoundPage extends Controller {

    @Override
    public HTTPResponse respondTo(HTTPRequest request) {
        return new HTTPResponse(NOT_FOUND);
    }
}
