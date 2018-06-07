package response;

public enum StatusLine {

    OK("200 OK"),
    NOT_FOUND("404 Not Found"),
    FOUND("302 Found");

    StatusLine(String codeAndMessage) {
        statusLine = codeAndMessage;
    }

    public final String statusLine;
}
