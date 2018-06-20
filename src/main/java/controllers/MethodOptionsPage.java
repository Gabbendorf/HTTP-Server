package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class MethodOptionsPage extends Controller {

    @Override
    public HTTPResponse options(HTTPRequest request) {
        return new HTTPResponse(OK, "Allow: GET,HEAD,POST,OPTIONS,PUT", "");
    }
}
