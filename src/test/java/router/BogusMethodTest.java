package router;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static response.StatusLine.NOT_ALLOWED;

public class BogusMethodTest {

    @Test
    public void respondsWithNotAllowed() {
        BogusMethod bogusMethod = new BogusMethod();

        HTTPResponse response = bogusMethod.dispatch(newRequest("DGEREG", "/"));

        assertEquals(NOT_ALLOWED.message, response.getStatusLine());
    }

    private HTTPRequest newRequest(String method, String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest(method, path, headers);
    }
}