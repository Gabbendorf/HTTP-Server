package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPResponseTest {

    @Test
    public void responseIsStatusLine() {
        HTTPResponse response = new HTTPResponse("200 OK");

        assertEquals("HTTP/1.1 200 OK", response.getResponse());
    }

    @Test
    public void responseIsStatusLineWithHeadersAndBody() {
        HTTPResponse response = new HTTPResponse("200 OK", "header", "hi");

        assertEquals("HTTP/1.1 200 OK\nheader\r\n\r\nhi", response.getResponse());
    }

    @Test
    public void getsBody() {
        HTTPResponse response = new HTTPResponse("", "", "hi");

        assertEquals("hi", response.getBody());
    }

    @Test
    public void getsHeaders() {
        HTTPResponse response = new HTTPResponse("", "headers", "");

        assertEquals("headers", response.getHeaders());
    }

    @Test
    public void getsStatusLine() {
        HTTPResponse response = new HTTPResponse("OK 200", "", "");

        assertEquals("OK 200", response.getStatusLine());
    }
}
