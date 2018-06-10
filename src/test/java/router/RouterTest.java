package router;

import controllers.HTTPMethod;
import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static controllers.HTTPMethod.GET;
import static controllers.HTTPMethod.INVALID;
import static org.junit.Assert.assertEquals;
import static response.StatusLine.NOT_ALLOWED;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class RouterTest {

    private Router router;

    @Before
    public void createInstance() {
        router = new Router();
    }

    @Test
    public void routesRequestAndProvidesResponse() {
        HTTPResponse response = router.route(newRequest(GET, "/"));

        assertEquals(OK.statusLine, response.getStatusLine());
    }

    @Test
    public void responseIsNotAllowedIfInvalidMethod() {
        HTTPResponse response = router.route((newRequest(INVALID, "/")));

        assertEquals(NOT_ALLOWED.statusLine, response.getStatusLine());
    }

    @Test
    public void responseIsNotFoundIfNotExistingPath() {
        HTTPResponse response = router.route(newRequest(GET, "/not-existing"));

        assertEquals(NOT_FOUND.statusLine, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method, String path) {
        return new HTTPRequest(method.name(), path);
    }
}
