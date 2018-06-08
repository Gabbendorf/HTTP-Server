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

public class PutMethodTest {

    private PutMethod put;

    @Before
    public void createInstance() {
        put = new PutMethod();
    }

    @Test
    public void respondsWithOkForPutTargetPath() {
        HTTPResponse response = put.dispatch(newRequest("/put-target"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsOkForMethodOptionsPath() {
        HTTPResponse response = put.dispatch(newRequest("/method_options"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsNotAllowedForMethodOptions2Path() {
        HTTPResponse response = put.dispatch(newRequest("/method_options2"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForFile1() {
        HTTPResponse response = put.dispatch(newRequest("/file1"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotFoundAsDefault() {
        HTTPResponse response = put.dispatch(newRequest("/not-existing"));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
