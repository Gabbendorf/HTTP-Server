package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPResponseTest {

    @Test
    public void getsStatusLine() {
        HTTPResponse response = new HTTPResponse("200 OK");

        assertEquals("HTTP/1.1 200 OK", response.statusLine());
    }
}
