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

public class PostMethodTest {

    private PostMethod post;

    @Before
    public void createInstance() {
        post = new PostMethod();
    }

    @Test
    public void respondsWithOkForFormPath() {
        HTTPResponse response = post.dispatch(newRequest("/form"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptionsPath() {
        HTTPResponse response = post.dispatch(newRequest("/method_options"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForMethodOptions2Path() {
        HTTPResponse response = post.dispatch(newRequest("/method_options2"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForFile1Path() {
        HTTPResponse response = post.dispatch(newRequest("/file1"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotAllowedForTextFilePath() {
        HTTPResponse response = post.dispatch(newRequest("/text-file.txt"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotFoundAsDefault() {
        HTTPResponse response = post.dispatch(newRequest("not-existing"));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
