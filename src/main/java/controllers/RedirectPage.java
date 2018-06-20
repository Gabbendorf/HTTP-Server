package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.FOUND;

public class RedirectPage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request) {
        return new HTTPResponse(FOUND, "Location: /", "");
    }
}
