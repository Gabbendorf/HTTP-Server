package controllers;

import org.junit.Before;
import org.junit.Test;
import request.HTTPPath;
import request.HTTPRequest;
import response.HTTPResponse;
import router.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

import static request.HTTPMethod.GET;
import static org.junit.Assert.*;
import static response.StatusLine.OK;
import static response.StatusLine.UNAUTHORIZED;

public class LogsControllerTest {

    private LogsController logsController;

    @Before
    public void createInstance() {
        logsController = new LogsController(new Logger());
    }

    @Test
    public void respondsWithUnauthorizedStatusCodeForMissingAuthorizationHeader() {
        HTTPResponse response = logsController.get(new HTTPRequest(GET.method, new HTTPPath("/logs"), headers("Localhost", "")));

        assertEquals(UNAUTHORIZED.message, response.getStatusLine());
    }

    @Test
    public void respondsWithWWWAuthenticateHeaderForMissingAuthorisationHeader() {
        HTTPResponse response = logsController.get(new HTTPRequest(GET.method, new HTTPPath("/logs"), headers("Localhost", "")));

        assertEquals("WWW-Authenticate: Basic realm=\"AccessToTheLogs\"", response.getHeaders());
    }

    @Test
    public void respondsWithOkForAuthorizedRequest() {
        HTTPResponse response = logsController.get(new HTTPRequest(GET.method, new HTTPPath("/logs"), headers("Authorization", "Basic YWRtaW46aHVudGVyMg==")));

        assertEquals(OK.message, response.getStatusLine());
    }

    private Map<String, String> headers(String key, String value) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put(key, value);
        return headers;
    }
}