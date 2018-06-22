package controllers;

import request.HTTPRequest;
import response.HTTPResponse;
import router.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static response.StatusLine.OK;
import static response.StatusLine.UNAUTHORIZED;

public class LogsController extends Controller {

    private final static String AUTHORIZATION = "Authorization";
    private Logger logger;

    public LogsController(Logger logger) {
        this.logger = logger;
    }

    @Override
    public HTTPResponse get(HTTPRequest request) {
        if (isAuthorized(request)) {
            return new HTTPResponse(OK, logger.getLogs());
        }
        return new HTTPResponse(UNAUTHORIZED, "WWW-Authenticate: Basic realm=\"AccessToTheLogs\"", "Not Authorized");
    }

    private boolean isAuthorized(HTTPRequest request) {
        return hasAuthorization(request) && areValidCredentials(request);
    }

    private boolean hasAuthorization(HTTPRequest request) {
        return request.getHeaders().containsKey(AUTHORIZATION);
    }

    private boolean areValidCredentials(HTTPRequest request) {
        String[] credentials = credentials(request);
        return credentials[0].equals("admin") && credentials[1].equals("hunter2");
    }

    private String[] credentials(HTTPRequest request) {
        String authorizationHeader = request.getHeaders().get(AUTHORIZATION).split("Basic")[1].trim();
        String decodedCredentials = new String(Base64.getUrlDecoder().decode(authorizationHeader), StandardCharsets.UTF_8);
        return decodedCredentials.split(":");
    }
}
