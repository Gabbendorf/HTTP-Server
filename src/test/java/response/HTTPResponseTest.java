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
    public void responseIsStatusLineWithHeaders() {
       HTTPResponse response = new HTTPResponse("200 OK", "header: ");

       assertEquals("HTTP/1.1 200 OK\nheader: ", response.getResponse());
    }
}
