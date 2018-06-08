package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.NOT_ALLOWED;

public class BogusMethod implements HTTPMethod {


    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        return new HTTPResponse(NOT_ALLOWED);
    }
}
