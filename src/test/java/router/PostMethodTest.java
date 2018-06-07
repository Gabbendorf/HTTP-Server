package router;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PostMethodTest {

    @Test
    public void respondsWithOk() {
        PostMethod post = new PostMethod();

        HTTPResponse response = post.dispatch(newRequest("/"));

        assertEquals("200 OK", response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
