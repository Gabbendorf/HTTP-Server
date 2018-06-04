package response;

public enum StatusLine {

    OK("200 OK"),
    NOT_FOUND("404 Not found");

    StatusLine(String codeAndMessage) {
        statusLine = codeAndMessage;
    }

    public final String statusLine;
}
