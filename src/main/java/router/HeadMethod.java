package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class HeadMethod implements HTTPMethod {

    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        HTTPResponse response;
        if (request.getPath().equals("/")) {
            response = new HTTPResponse(OK);
        } else {
            response = new HTTPResponse(NOT_FOUND);
        }
        return response;
    }
}
