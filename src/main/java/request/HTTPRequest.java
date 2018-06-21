package request;

import java.util.Map;

public class HTTPRequest {

    private String body;
    private String method;
    private String path;
    private Map<String, String> headers;

    public HTTPRequest(String method, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public HTTPRequest(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getRequestLine() {
        String protocolVersion = "HTTP/1.1";
        String SPACE = " ";
        return getMethod() + SPACE + getPath() + SPACE + protocolVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
