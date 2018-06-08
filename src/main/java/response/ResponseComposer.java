package response;

public class ResponseComposer {

    private StatusLine statusLine;
    private String headers;
    private String body;
    private static String PROTOCOL_VERSION = "HTTP/1.1 ";

    public ResponseComposer(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public ResponseComposer(StatusLine statusLine, String headers, String body) {
        this.statusLine = statusLine;
        this.headers = headers;
        this.body = body;
    }

    public String statusLine() {
        return statusLine.message;
    }

    public String response() {
        return PROTOCOL_VERSION + statusLine() + "\n" + headers + "\r\n\r\n" + body;
    }
}
