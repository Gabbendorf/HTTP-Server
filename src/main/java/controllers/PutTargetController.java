package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class PutTargetController extends Controller {

    @Override
    public HTTPResponse put(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
