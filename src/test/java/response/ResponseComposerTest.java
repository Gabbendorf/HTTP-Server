package response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.OK;

public class ResponseComposerTest {

    private ResponseComposer responseComposer;

    @Before
    public void createInstance() {
        responseComposer = new ResponseComposer();
    }

    @Test
    public void preparesStatusLine() {
        assertEquals("200 OK", responseComposer.prepare(OK));
    }

    @Test
    public void composesResponseWithStatusLine() {
        assertEquals("HTTP/1.1 200 OK", responseComposer.composeWith(OK));
    }

    @Test
    public void composesResponseWithStatusLineAndBody() {
        assertEquals("HTTP/1.1 200 OK\n\r\n\r\nbody", responseComposer.composeWith(OK, "body"));
    }

    @Test
    public void composesResponseWithStatusLineHeadersAndBody() {
        String response = responseComposer.composeWith(OK, "headers: ", "body");

        assertEquals("HTTP/1.1 200 OK\nheaders: \r\n\r\nbody", response);
    }
}
