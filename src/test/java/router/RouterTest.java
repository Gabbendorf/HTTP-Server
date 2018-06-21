package router;

import request.HTTPMethod;
import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import static request.HTTPMethod.GET;
import static request.HTTPMethod.INVALID;
import static org.junit.Assert.assertEquals;
import static response.StatusLine.NOT_ALLOWED;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class RouterTest {

    private Router router;
    private Logger logger;

    @Before
    public void createInstances() {
        logger = new Logger();
        router = new Router(logger, "");
    }

    @Test
    public void registersLogs() {
        router.route(newRequest(GET,  "/"));

        assertEquals("GET / HTTP/1.1\n", logger.getLogs());
    }

    @Test
    public void routesRequestAndProvidesResponse() {
        HTTPResponse response = router.route(newRequest(GET, "/"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void responseIsNotAllowedIfInvalidMethod() {
        HTTPResponse response = router.route((newRequest(INVALID, "/")));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void responseIsNotFoundIfNotExistingPath() {
        HTTPResponse response = router.route(newRequest(GET, "/not-existing"));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(HTTPMethod method, String path) {
        return new HTTPRequest(method.name(), path);
    }
}
