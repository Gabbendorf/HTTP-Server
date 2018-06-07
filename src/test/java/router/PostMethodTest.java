package router;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PostMethodTest {

    private PostMethod post;

    @Before
    public void createInstance() {
        post = new PostMethod();
    }

    @Test
    public void respondsWithOk() {
        HTTPResponse response = post.dispatch(newRequest("/"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptionsPath() {
        HTTPResponse response = post.dispatch(newRequest("/method_options"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForMethodOptions2Path() {
        HTTPResponse response = post.dispatch(newRequest("/method_options2"));

        assertEquals("405 Method Not Allowed", response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
