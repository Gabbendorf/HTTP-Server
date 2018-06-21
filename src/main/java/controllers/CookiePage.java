package controllers;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class CookiePage extends Controller {

    @Override
    public HTTPResponse get(HTTPRequest request ) {
        if (hasCookieHeader(request)) {
            return new HTTPResponse(OK, String.format("mmmm %s", cookieType(request)));
        }
        return new HTTPResponse(OK, String.format("Set-Cookie: %s", cookie(request)), "Eat");
    }

    private boolean hasCookieHeader(HTTPRequest request) {
        return request.getHeaders().containsKey("Cookie");
    }

    private String cookie(HTTPRequest request) {
        return request.getPath().split("\\?")[1];
    }

    private String cookieType(HTTPRequest request) {
        return request.getHeaders().get("Cookie").split("=")[1];
    }
}
