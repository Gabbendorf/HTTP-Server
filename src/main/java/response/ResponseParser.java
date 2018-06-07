package response;

public class ResponseParser {

    private String statusLine;

    public ResponseParser(String statusLine) {
        this.statusLine = statusLine;
    }

    public String statusCode() {
        return statusLineElements()[0];
    }

    public String statusMessage() {
        return statusLineElements()[1];
    }

    private String[] statusLineElements() {
        return statusLine.split(" ");
    }
}
