package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class HomePage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK);
    }

    @Override
    public HTTPResponse head(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
