package request;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestParserTest {

    private static RequestParser parser;
    private String requestLine;
    private List<String> headers;

    @Before
    public void setUpParser() {
        headers = Arrays.asList("Host: localhost", "Cookie: type=chocolate");
        requestLine = "GET / HTTP/1.1";
        parser = new RequestParser();
    }

    @Test
    public void parsesRequestMethod() {
        String method = parser.method(requestLine);

        assertEquals("GET", method);
    }

    @Test
    public void parsesRequestPath() {
        HTTPPath path = parser.path(requestLine);

        assertEquals("/", path.getFullPath());
    }

    @Test
    public void parsesHeaders() {
        Map<String, String> headers = parser.headers(this.headers);

        assertTrue(headers.containsKey("Host"));
        assertTrue(headers.containsKey("Cookie"));
    }
}
