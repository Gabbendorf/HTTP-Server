public class HTTPRequest {

    private final String line;
    private final String[] singleLineElements;

    public HTTPRequest(String request) {
        this.line = request;
        this.singleLineElements = line.split(" ");
    }

    public String line() {
        return line;
    }

    public String verb() {
        return singleLineElements[0];
    }

    public String route() {
        return singleLineElements[1];
    }
}
