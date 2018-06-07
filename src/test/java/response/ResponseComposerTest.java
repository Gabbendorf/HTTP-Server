package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.OK;

public class ResponseComposerTest {

    @Test
    public void composesStatusLine() {
        ResponseComposer responseComposer = new ResponseComposer(OK);

        assertEquals("200 OK", responseComposer.statusLine());
    }

    @Test
    public void composesResponseWithHeadersAndBody() {
        ResponseComposer responseComposer = new ResponseComposer(OK, "headers", "body");

        assertEquals("HTTP/1.1 200 OK\nheaders\r\n\r\nbody", responseComposer.response());
    }
}
