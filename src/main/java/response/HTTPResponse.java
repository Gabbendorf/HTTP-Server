package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private ResponseComposer responseComposer;
    private String body;
    private String headers;
    private String statusLine;
    private String response;

    public HTTPResponse(StatusLine statusLine) {
        this.responseComposer = new ResponseComposer(statusLine);
        this.statusLine = responseComposer.statusLine();
        this.response = PROTOCOL_VERSION + this.statusLine;
    }

    public HTTPResponse(StatusLine statusLine, String headers, String body) {
        this.responseComposer = new ResponseComposer(statusLine, headers, body);
        this.statusLine = responseComposer.statusLine();
        this.headers = headers;
        this.body = body;
        this.response = responseComposer.response();
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
