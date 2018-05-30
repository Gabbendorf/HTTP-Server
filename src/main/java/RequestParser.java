public class RequestParser {

    private final String lineRequest;

    public RequestParser(String request) {
        this.lineRequest = request.split("\n")[0];
    }

    public String parseMethod() {
        return lineRequest.split(" ")[0];
    }

    public String parseRoute() {
        return lineRequest.split(" ")[1];
    }

    public String parseHTTPVersion() {
        return lineRequest.split(" ")[2];
    }

    public String parseLineRequest() {
        return lineRequest;
    }
}
