import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPRequestTest {

    @Test
    public void getsRequestLine() {
        HTTPRequest httpRequest = new HTTPRequest("GET / HTTP/1.1\n");

        String requestLine = httpRequest.line();

        assertEquals("GET / HTTP/1.1\n", requestLine);
    }

    @Test
    public void getsRequestVerb() {
        HTTPRequest httpRequest = new HTTPRequest("GET / HTTP/1.1\n");

        String verb = httpRequest.verb();

        assertEquals("GET", verb);
    }

    @Test
    public void getsRequestRoute() {
        HTTPRequest httpRequest = new HTTPRequest("GET / HTTP/1.1\n");

        String route = httpRequest.route();

        assertEquals("/", route);
    }
}
