package request;

public class RequestParser {

    private final String requestLine;

    public RequestParser(String request) {
        this.requestLine = request.split("\n")[0];
    }

    public String parseMethod() {
        return requestLine.split(" ")[0];
    }

    public String parseRoute() {
        return requestLine.split(" ")[1];
    }

    public String parseHTTPVersion() {
        return requestLine.split(" ")[2];
    }

    public String parseRequestLine() {
        return requestLine;
    }
}
