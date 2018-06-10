package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class FilePage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
