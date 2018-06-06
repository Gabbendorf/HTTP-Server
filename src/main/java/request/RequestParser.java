package request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

    private final String requestLine;
    private final List<String> request;

    public RequestParser(List<String> request) {
        this.request = request;
        this.requestLine = request.get(0);
    }

    public String method() {
        return requestLineElements()[0];
    }

    public String path() {
        return requestLineElements()[1];
    }

    public String HTTPVersion() {
        return requestLineElements()[2];
    }

    public Map<String, String> headers() {
        Map<String, String> parsedHeaders = new LinkedHashMap<>();
        for (int i = 1; i < request.size(); i++) {
            String[] headerElements = request.get(i).split(":");
            parsedHeaders.put(headerElements[0], headerElements[1]);
        }
        return parsedHeaders;
    }

    private String[] requestLineElements() {
       return requestLine.split(" ");
    }
}
