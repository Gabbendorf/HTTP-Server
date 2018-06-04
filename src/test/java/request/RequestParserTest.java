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
        String method = parser.method();

        assertEquals("GET", method);
    }

    @Test
    public void parsesRequestUrl() {
        String url = parser.url();

        assertEquals("/", url);
    }

    @Test
    public void parsesProtocolVersion() {
        String httpVersion = parser.HTTPVersion();

        assertEquals("HTTP/1.1", httpVersion);
    }

    @Test
    public void getsLineRequest() {
        String requestLine = parser.requestLine();

        assertEquals("GET / HTTP/1.1", requestLine);
    }
}
