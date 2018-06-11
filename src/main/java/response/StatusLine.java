package response;

public enum StatusLine {

    OK("200 OK"),
    NOT_FOUND("404 Not Found"),
    FOUND("302 Found"),
    NOT_ALLOWED("405 Method Not Allowed"),
    TEAPOT("418 I'm a teapot");

    StatusLine(String statusLine) {
        message = statusLine;
    }

    public final String message;
}
