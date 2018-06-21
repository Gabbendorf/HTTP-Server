package request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {

    public String method(String requestLine) {
        return requestLineElements(requestLine)[0];
    }

    public String path(String requestLine) {
        return requestLineElements(requestLine)[1];
    }

    public Map<String, String> headers(List<String> headers) {
        Map<String, String> parsedHeaders = new LinkedHashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String[] headerElements = headers.get(i).split(":");
            parsedHeaders.put(headerElements[0], headerElements[1]);
        }
        return parsedHeaders;
    }

    private String[] requestLineElements(String requestLine) {
       return requestLine.split(" ");
    }
}
