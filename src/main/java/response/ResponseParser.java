package response;

public class ResponseParser {

    private StatusLine statusLine;

    public ResponseParser(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public String statusLine() {
        return statusLine.toString;
    }
}
