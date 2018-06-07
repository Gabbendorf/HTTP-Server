package router;

import org.junit.Test;
import request.HTTPRequest;
import response.HTTPResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OptionsMethodTest {

    @Test
    public void respondsWithMethodsAllowedInHeaders() {
        OptionsMethod optionsMethod = new OptionsMethod();

        HTTPResponse response = optionsMethod.dispatch(newRequest("/method_options"));

        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.getHeaders());
    }

    @Test
    public void respondsWithMethodsAllowedForMethodOptions2() {
        OptionsMethod optionsMethod = new OptionsMethod();

        HTTPResponse response = optionsMethod.dispatch(newRequest("/method_options2"));

        assertEquals("Allow: GET,HEAD,OPTIONS", response.getHeaders());
    }

    private HTTPRequest newRequest(String path) {
        Map<String, String> headers = new HashMap<String, String>() {{
            put("", "");
        }};
        return new HTTPRequest("HEAD", path, headers);
    }
}
