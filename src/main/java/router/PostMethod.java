package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.NOT_ALLOWED;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class PostMethod implements HTTPMethod {

    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        String path = request.getPath();
        switch (path) {
            case "/method_options":
                return new HTTPResponse(OK);
            case "/method_options2":
                return new HTTPResponse(NOT_ALLOWED);
            case "/form":
                return new HTTPResponse(OK);
            case "/file1":
                return new HTTPResponse(NOT_ALLOWED);
            case "/text-file.txt":
                return new HTTPResponse(NOT_ALLOWED);
            default:
                return new HTTPResponse(NOT_FOUND);
        }
    }
}
