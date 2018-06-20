package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class FormPage extends Controller {

    @Override
    public HTTPResponse post(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
