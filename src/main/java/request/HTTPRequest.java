package request;

import java.util.Map;

public class HTTPRequest {

    private String method;
    private String path;
    private Map<String, String> headers;

    public HTTPRequest(String method, String path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
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
}
