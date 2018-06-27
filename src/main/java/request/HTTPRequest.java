package request;

import java.util.Map;

public class HTTPRequest {

    private String body;
    private String method;
    private HTTPPath path;
    private Map<String, String> headers;

    public HTTPRequest(String method, HTTPPath path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public HTTPRequest(String method, HTTPPath path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
    }

    public HTTPRequest(String method, HTTPPath path) {
        this.method = method;
        this.path = path;
    }

    public HTTPRequest(String method, HTTPPath path, String body) {
        this.method = method;
        this.path = path;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path.getFullPath();
    }

    public String getFirstPathSegment() {
        return path.getFirstSegment();
    }

    public String getLastPathSegment() {
        return path.getLastSegment();
    }

    public String getQueryString() {
        return path.getQueryString();
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
