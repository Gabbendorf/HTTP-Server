package request;

public class HTTPRequest {

    private final RequestParser parser;

    public HTTPRequest(RequestParser parser) {
        this.parser = parser;
    }

    public String method() {
        return parser.parseMethod();
    }

    public String route() {
        return parser.parseRoute();
    }

    public String requestLine() {
        return parser.parseRequestLine();
    }

    public String protocolVersion() {
        return parser.parseHTTPVersion();
    }
}
