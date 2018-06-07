package router;

import request.HTTPRequest;
import response.HTTPResponse;

public interface HTTPMethod {

    HTTPResponse dispatch(HTTPRequest request);
}
