package response;

public enum StatusLine {

    OK("200 OK"),
    NOT_FOUND("404 Not Found"),
    FOUND("302 Found"),
    NOT_ALLOWED("405 Method Not Allowed"),
    TEAPOT("418 I'm a teapot"),
    NO_CONTENT("204 No Content"),
    PRECONDITION_FAILED("412 precondition failed"),
    UNAUTHORIZED("401 Unauthorized Request"),
    INTERNAL_SERVER_ERROR("500 Internal Server Error");

    StatusLine(String statusLine) {
        message = statusLine;
    }

    public final String message;
}
