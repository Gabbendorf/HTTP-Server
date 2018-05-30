package request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPRequestTest {

    private HTTPRequest httpRequest;

    @Before
    public void setUpHTTPRequest() {
        httpRequest = new HTTPRequest(new RequestParser("GET / HTTP/1.1\n"));
    }

    @Test
    public void getsRequestLine() {
        String requestLine = httpRequest.lineRequest();

        assertEquals("GET / HTTP/1.1", requestLine);
    }

    @Test
    public void getsRequestVerb() {
        String method = httpRequest.method();

        assertEquals("GET", method);
    }

    @Test
    public void getsRequestRoute() {
        String route = httpRequest.route();

        assertEquals("/", route);
    }

    @Test
    public void getsHTTPVersion() {
        String httpVersion = httpRequest.protocolVersion();

        assertEquals("HTTP/1.1", httpVersion);
    }
}
