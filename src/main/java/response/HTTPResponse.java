package response;

public class HTTPResponse {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private ResponseParser responseParser;
    private String body;
    private String headers;
    private String statusLine;
    private String response;

    public HTTPResponse(StatusLine statusLine) {
        this.responseParser = new ResponseParser(statusLine);
        this.statusLine = responseParser.statusLine();
        this.response = PROTOCOL_VERSION + this.statusLine;
    }

    public HTTPResponse(StatusLine statusLine, String headers, String body) {
        this.responseParser = new ResponseParser(statusLine);
        this.statusLine = responseParser.statusLine();
        this.headers = headers;
        this.body = body;
        this.response = PROTOCOL_VERSION + this.statusLine + "\n" + headers + "\r\n\r\n" + body;
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
