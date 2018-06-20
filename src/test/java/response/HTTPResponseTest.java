package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.OK;

public class HTTPResponseTest {

    @Test
    public void responseIsStatusLine() {
        HTTPResponse response = new HTTPResponse(OK);

        assertEquals("HTTP/1.1 200 OK", response.getResponse());
    }

    @Test
    public void responseIsStatusLineWithHeadersAndBody() {
        HTTPResponse response = new HTTPResponse(OK, "header", "body");

        assertEquals("HTTP/1.1 200 OK\nheader\r\n\r\nbody", response.getResponse());
    }

    @Test
    public void responseIsStatusLineWithBody() {
        HTTPResponse response = new HTTPResponse(OK, "body");

        assertEquals("HTTP/1.1 200 OK\n\r\n\r\nbody", response.getResponse());
    }

    @Test
    public void getsBody() {
        HTTPResponse response = new HTTPResponse(OK, "", "hi");

        assertEquals("hi", response.getBody());
    }

    @Test
    public void getsHeaders() {
        HTTPResponse response = new HTTPResponse(OK, "headers", "");

        assertEquals("headers", response.getHeaders());
    }

    @Test
    public void getsStatusLine() {
        HTTPResponse response = new HTTPResponse(OK, "", "");

        assertEquals("200 OK", response.getStatusLine());
    }
}
