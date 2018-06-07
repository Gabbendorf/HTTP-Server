package request;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HTTPRequestTest {

    private HTTPRequest httpRequest;

    @Before
    public void setUpHTTPRequest() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Localhost", "/");
        httpRequest = new HTTPRequest("GET", "/", headers);
    }

    @Test
    public void getsRequestVerb() {
        String method = httpRequest.getMethod();

        assertEquals("GET", method);
    }

    @Test
    public void getsRequestPath() {
        String path = httpRequest.getPath();

        assertEquals("/", path);
    }

    @Test
    public void getsRequestLine() {
        String requestLine = httpRequest.getRequestLine();

        assertEquals("GET / HTTP/1.1", requestLine);
    }

    @Test
    public void getsHeaders() {
        Map<String, String> headers = httpRequest.getHeaders();

        assertTrue(headers.containsKey("Localhost"));
    }
}
