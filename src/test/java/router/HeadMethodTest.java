package router;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HeadMethodTest {

    @Test
    public void respondsWithOk() {
        HeadMethod head = new HeadMethod();

        HTTPResponse response = head.dispatch(newRequest("/"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithNotFound() {
        HeadMethod head = new HeadMethod();

        HTTPResponse response = head.dispatch(newRequest("/not-existing"));

        assertEquals("404 Not Found", response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, "HTTP/1.1", headers);
    }
}
