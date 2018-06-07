package router;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PutMethodTest {

    private PutMethod put;

    @Before
    public void createInstance() {
        put = new PutMethod();
    }

    @Test
    public void respondsWithOk() {
        HTTPResponse response = put.dispatch(newRequest("/"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsOkForMethodOptionsPath() {
        HTTPResponse response = put.dispatch(newRequest("/method_options"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsNotAllowedForMethodOptions2Path() {
        HTTPResponse response = put.dispatch(newRequest("/method_options2"));

        assertEquals("405 Method Not Allowed", response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
