package request;

import java.util.Arrays;
import java.util.List;

public class HTTPPath {

    private String path;
    private List<String> segments;

    public HTTPPath(String path) {
        this.path = path;
        this.segments = Arrays.asList(getFullPath().split("/"));
    }

    public String getFullPath() {
        if (containsQueryString()) {
            return pathWithoutQueryString();
        }
        return path;
    }

    private String pathWithoutQueryString() {
        return separatePathFromQueryString()[0];
    }

    public String getQueryString() {
        return separatePathFromQueryString()[1];
    }

    private String[] separatePathFromQueryString() {
        return path.split("\\?");
    }

    public String getLastSegment() {
        return "/" + segments.get(segments.size() - 1);
    }

    public String getSegmentNumber(int number) {
        return "/" + segments.get(number);
    }

    private boolean containsQueryString() {
        return path.contains("?");
    }
}
