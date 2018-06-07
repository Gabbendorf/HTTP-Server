package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.*;

public class OptionsMethod implements HTTPMethod {

    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        String path = request.getPath();
        switch (path) {
            case "/method_options":
                return new HTTPResponse(OK, "Allow: GET,HEAD,POST,OPTIONS,PUT", "");
            case "/method_options2":
                return new HTTPResponse(OK, "Allow: GET,HEAD,OPTIONS", "");
            default:
                return new HTTPResponse(NOT_FOUND);
        }
    }
}
