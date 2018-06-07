package router;

import request.HTTPRequest;
import response.HTTPResponse;

import static response.StatusLine.OK;

public class PostMethod implements HTTPMethod {

    @Override
    public HTTPResponse dispatch(HTTPRequest request) {
        return new HTTPResponse(OK);
    }
}
