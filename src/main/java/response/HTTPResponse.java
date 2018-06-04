package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private String statusCode;
    private String statusMessage;

    public HTTPResponse(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String statusLine() {
        return PROTOCOL_VERSION + this.statusCode + " " + this.statusMessage;
    }
}
