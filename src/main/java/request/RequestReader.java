package request;

import exceptions.InputStreamException;

import java.io.*;
import java.util.*;

public class RequestReader {

    private final BufferedReader bufferedReader;

    public RequestReader(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }


    public HTTPRequest readRequest() {
        List<String> request = new ArrayList<>();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (isEndOfHeaders(line)) {
                    break;
                }
                request.add(line);
            }
        } catch (IOException e) {
            throw new InputStreamException(e);
        }
        RequestParser parser = new RequestParser(request);
        return new HTTPRequest(parser.method(), parser.path(), parser.HTTPVersion(), parser.headers());
    }

    private boolean isEndOfHeaders(String line) {
        return line.equals("");
    }
}
