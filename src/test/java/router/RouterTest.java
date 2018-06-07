package router;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void routesRequestAndProvidesResponse() {
        Router router = new Router();

        HTTPResponse response = router.route(newRequest("GET", "/"));

        assertEquals("HTTP/1.1 200 OK", response.getResponse());
    }

    private HTTPRequest newRequest(String method, String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest(method, path, headers);
    }
}
