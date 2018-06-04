package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private String statusLine;

    public HTTPResponse(String statusLine) {
        this.statusLine = statusLine;
    }

    public String statusLine() {
        return PROTOCOL_VERSION + statusLine;
    }
}
