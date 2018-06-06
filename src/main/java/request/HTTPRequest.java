package request;

import java.util.Map;

public class HTTPRequest {

    private String method;
    private String path;
    private String protocolVersion;
    private Map<String, String> headers;
    private static String SPACE = " ";

    public HTTPRequest(String method, String path, String protocolVersion, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.protocolVersion = protocolVersion;
        this.headers = headers;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPath() {
        return this.path;
    }

    public String getRequestLine() {
        return getMethod() + SPACE + getPath() + SPACE + this.protocolVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
