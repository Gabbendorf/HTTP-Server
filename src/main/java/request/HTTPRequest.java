package request;

public class HTTPRequest {

    private String method;
    private String url;
    private String protocolVersion;
    private static String SPACE = " ";

    public HTTPRequest(String method, String url, String protocolVersion) {
        this.method = method;
        this.url = url;
        this.protocolVersion = protocolVersion;
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
}
