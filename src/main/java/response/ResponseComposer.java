package response;

public class ResponseComposer {

    private static String PROTOCOL_VERSION = "HTTP/1.1 ";
    private static String NEW_LINE = "\n";
    private static String DOUBLE_NEW_LINE = "\r\n\r\n";

    public String composeWith(StatusLine statusLine) {
        return PROTOCOL_VERSION + prepare(statusLine);
    }

    public String composeWith(StatusLine statusLine, String body) {
        return PROTOCOL_VERSION + prepare(statusLine) + NEW_LINE + DOUBLE_NEW_LINE + body;
    }

    public String composeWith(StatusLine statusLine, String headers, String body) {
        return PROTOCOL_VERSION + prepare(statusLine) + NEW_LINE + headers + DOUBLE_NEW_LINE + body;
    }

    public String prepare(StatusLine statusLine) {
        return statusLine.message;
    }
}
