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

    @Before
    public void setUpParser() {
        List<String> request = Arrays.asList("GET / HTTP/1.1", "Host: localhost", "Cookie: type=chocolate");
        parser = new RequestParser(request);
    }

    @Test
    public void parsesRequestMethod() {
        String method = parser.method();

        assertEquals("GET", method);
    }

    @Test
    public void parsesRequestPath() {
        String path = parser.path();

        assertEquals("/", path);
    }

    @Test
    public void parsesProtocolVersion() {
        String httpVersion = parser.HTTPVersion();

        assertEquals("HTTP/1.1", httpVersion);
    }

    @Test
    public void parsesHeaders() {
        Map<String, String> headers = parser.headers();

        assertTrue(headers.containsKey("Host"));
        assertTrue(headers.containsKey("Cookie"));
    }
}
