package request;

public class RequestParser {

    private final String request;

    public RequestParser(String request) {
        this.request = request;
    }

    public String method() {
        return requestLineElements()[0];
    }

    public String url() {
        return requestLineElements()[1];
    }

    public String HTTPVersion() {
        return requestLineElements()[2];
    }

    public String requestLine() {
        return request.split("\n")[0];
    }

    private String[] requestLineElements() {
       return requestLine().split(" ");
    }
}
