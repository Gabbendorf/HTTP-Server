package router;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.NOT_ALLOWED;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class HeadMethodTest {

    private HeadMethod head;

    @Before
    public void createInstance() {
        head = new HeadMethod();
    }

    @Test
    public void respondsWithOkForHomePath() {
        HTTPResponse response = head.dispatch(newRequest("/"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptions2Path() {
        HTTPResponse response = head.dispatch(newRequest("/method_options2"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptionsPath() {
        HTTPResponse response = head.dispatch(newRequest("/method_options"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForFile1Path() {
        HTTPResponse response = head.dispatch(newRequest("/file1"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotFoundAsDefault() {
        HTTPResponse response = head.dispatch(newRequest("/not-existing"));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
