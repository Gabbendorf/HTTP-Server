package request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private static RequestParser parser;

    @Before
    public void setUpParser() {
        parser = new RequestParser("GET / HTTP/1.1");
    }

    @Test
    public void parsesRequestMethod() {
        String method = parser.parseMethod();

        assertEquals("GET", method);
    }

    @Test
    public void parsesRequestRoute() {
        String route = parser.parseRoute();

        assertEquals("/", route);
    }

    @Test
    public void parsesProtocolVersion() {
        String httpVersion = parser.parseHTTPVersion();

        assertEquals("HTTP/1.1", httpVersion);
    }

    @Test
    public void getsLineRequest() {
        String lineRequest = parser.parseLineRequest();

        assertEquals("GET / HTTP/1.1", lineRequest);
    }
}
