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
                return new HTTPResponse(OK.statusLine, "Set-Cookie: type=chocolate", "Eat");
            case "/redirect":
                return new HTTPResponse(FOUND.statusLine, "Location: /", "");
            case "/eat_cookie":
                return new HTTPResponse(OK.statusLine, "Cookie: type=chocolate", "mmmm chocolate");
            case "/":
                return new HTTPResponse(OK.statusLine);
            default:
                return new HTTPResponse(NOT_FOUND.statusLine);
        }
    }
}
