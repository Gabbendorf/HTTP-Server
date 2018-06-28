package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class CookieController extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request ) {
        if (hasCookieHeader(request)) {
            return new HTTPResponse(OK, String.format("mmmm %s", cookieType(request)));
        }
        return new HTTPResponse(OK, String.format("Set-Cookie: %s", request.getQueryString()), "Eat");
    }

    private boolean hasCookieHeader(HTTPRequest request) {
        return request.getHeaders().containsKey("Cookie");
    }

    private String cookieType(HTTPRequest request) {
        return request.getHeaders().get("Cookie").split("=")[1];
    }
}
