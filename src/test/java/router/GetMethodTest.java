package router;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.NOT_FOUND;
import static response.StatusLine.OK;

public class GetMethodTest {

    private GetMethod get;

    @Before
    public void createInstance() {
        get = new GetMethod();
    }

    @Test
    public void respondsWithEatBodyAndSetsCookie() {
        HTTPResponse response = get.dispatch(newRequest("/cookie?type=chocolate"));

        assertEquals("Set-Cookie: type=chocolate", response.getHeaders());
        assertEquals("Eat", response.getBody());
    }

    @Test
    public void respondsWithLocationHeaderAndNoBody() {
        HTTPResponse response = get.dispatch(newRequest("/redirect"));

        assertEquals("Location: /", response.getHeaders());
        assertEquals("", response.getBody());
    }

    @Test
    public void respondsWithCookieHeaderAndBody() {
        HTTPResponse response = get.dispatch(newRequest("/eat_cookie"));

        assertEquals("Cookie: type=chocolate", response.getHeaders());
        assertEquals("mmmm chocolate", response.getBody());
    }

    @Test
    public void respondsWithOkForHomePath() {
        HTTPResponse response = get.dispatch(newRequest("/"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptionsPath() {
        HTTPResponse response = get.dispatch(newRequest("/method_options"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptions2Path() {
        HTTPResponse response = get.dispatch(newRequest("/method_options2"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForFile1Path() {
        HTTPResponse response = get.dispatch(newRequest("/file1"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithOkForTextFilePath() {
        HTTPResponse response = get.dispatch(newRequest("/text-file.txt"));

        assertEquals(OK.message, response.getStatusLine());
    }

    @Test
    public void respondsWithNotFound() {
        HTTPResponse response = get.dispatch(newRequest("/not-existing"));

        assertEquals(NOT_FOUND.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("GET", path, headers);
    }
}
