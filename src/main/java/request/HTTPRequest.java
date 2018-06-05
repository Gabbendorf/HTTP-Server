package request;

import java.util.Map;

public class HTTPRequest {

    private String method;
    private String url;
    private String protocolVersion;
    private Map<String, String> headers;
    private static String SPACE = " ";

    public HTTPRequest(String method, String url, String protocolVersion, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.protocolVersion = protocolVersion;
        this.headers = headers;
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }

    public String getRequestLine() {
        return getMethod() + SPACE + getUrl() + SPACE + this.protocolVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
