package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private String body;
    private String headers;
    private String statusLine;
    private String response;

    public HTTPResponse(String statusLine) {
        this.statusLine = statusLine;
        this.response = PROTOCOL_VERSION + statusLine;
    }

    public HTTPResponse(String statusLine, String headers, String body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
        this.response = PROTOCOL_VERSION + statusLine + "\n" + headers + "\r\n\r\n" + body;
    }

    public String getResponse() {
        return response;
    }

    public String getBody() {
        return body;
    }

    public String getHeaders() {
        return headers;
    }

    public String getStatusLine() {
        return statusLine;
    }
}
