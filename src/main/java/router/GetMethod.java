package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.FOUND;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class GetMethod implements HTTPMethod {

    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        String path = request.getPath();
        switch (path) {
            case "/cookie?type=chocolate":
                return new HTTPResponse(OK, "Set-Cookie: type=chocolate", "Eat");
            case "/redirect":
                return new HTTPResponse(FOUND, "Location: /", "");
            case "/eat_cookie":
                return new HTTPResponse(OK, "Cookie: type=chocolate", "mmmm chocolate");
            case "/method_options2":
                return new HTTPResponse(OK);
            case "/method_options":
                return new HTTPResponse(OK);
            case "/":
                return new HTTPResponse(OK);
            default:
                return new HTTPResponse(NOT_FOUND);
        }
    }
}
