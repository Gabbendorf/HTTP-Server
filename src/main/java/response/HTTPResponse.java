package response;

public class HTTPResponse {

    private ResponseComposer responseComposer;
    private String body;
    private String headers;
    private String statusLine;
    private String response;

    public HTTPResponse(StatusLine statusLine) {
        this.responseComposer = new ResponseComposer();
        this.statusLine = responseComposer.prepare(statusLine);
        this.response = responseComposer.composeWith(statusLine);
    }

    public HTTPResponse(StatusLine statusLine, String headers, String body) {
        this.responseComposer = new ResponseComposer();
        this.statusLine = responseComposer.prepare(statusLine);
        this.headers = headers;
        this.body = body;
        this.response = responseComposer.composeWith(statusLine, headers, body);
    }

    public HTTPResponse(StatusLine statusLine, String body) {
        this.responseComposer = new ResponseComposer();
        this.statusLine = responseComposer.prepare(statusLine);
        this.body = body;
        this.response = responseComposer.composeWith(statusLine, body);
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
