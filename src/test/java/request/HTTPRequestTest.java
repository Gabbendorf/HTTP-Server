package request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPRequestTest {

    private HTTPRequest httpRequest;

    @Before
    public void setUpHTTPRequest() {
        httpRequest = new HTTPRequest("GET", "/", "HTTP/1.1");
    }

    @Test
    public void getsRequestVerb() {
        String method = httpRequest.getMethod();

        assertEquals("GET", method);
    }

    @Test
    public void getsRequestRoute() {
        String route = httpRequest.getUrl();

        assertEquals("/", route);
    }

    @Test
    public void getsRequestLine() {
        String requestLine = httpRequest.getRequestLine();

        assertEquals("GET / HTTP/1.1", requestLine);
    }
}
