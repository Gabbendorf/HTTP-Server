package router;

import org.junit.Before;
import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptionsPath() {
        HTTPResponse response = get.dispatch(newRequest("/method_options"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithOkForMethodOptions2Path() {
        HTTPResponse response = get.dispatch(newRequest("/method_options2"));

        assertEquals("200 OK", response.getStatusLine());
    }

    @Test
    public void respondsWithNotFound() {
        HTTPResponse response = get.dispatch(newRequest("/not-existing"));

        assertEquals("404 Not Found", response.getStatusLine());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("GET", path, headers);
    }
}
