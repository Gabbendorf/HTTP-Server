package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;
import static response.StatusLine.TEAPOT;

public class TeaPage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        if (!request.getPath().equals("/tea")) {
            return new HTTPResponse(TEAPOT, "I'm a teapot");
        }
        return new HTTPResponse(OK);
    }
}
