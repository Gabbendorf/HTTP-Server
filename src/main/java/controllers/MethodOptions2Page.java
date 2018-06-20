package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class MethodOptions2Page extends Controller {

    @Override
    public HTTPResponse options(HTTPRequest request) {
        return new HTTPResponse(OK, "Allow: GET,HEAD,OPTIONS", "");
    }

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK);
    }

    @Override
    public HTTPResponse head(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
