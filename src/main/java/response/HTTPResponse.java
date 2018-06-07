package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private String response;

    public HTTPResponse(String statusLine) {
        this.response = PROTOCOL_VERSION + statusLine;
    }

    public HTTPResponse(String statusLine, String headers) {
        this.response = PROTOCOL_VERSION + statusLine + "\n" + headers;
    }

    public HTTPResponse(String statusLine, String headers, String body) {
        this.response = PROTOCOL_VERSION + statusLine + "\n" + headers + "\r\n\r\n" + body;
    }

    public String getResponse() {
        return response;
    }
}
