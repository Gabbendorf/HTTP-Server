package request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPPathTest {

    private HTTPPath path;

    @Before
    public void createInstance() {
        path = new HTTPPath("/path/to?name=gabi");
    }

    @Test
    public void getsFullPathWithoutQueryString() {
        String fullPath = path.getFullPath();

        assertEquals("/path/to", fullPath);
    }

    @Test
    public void getQueryString() {
        String queryString = path.getQueryString();

        assertEquals("name=gabi", queryString);
    }

    @Test
    public void getsCorrespondingSegment() {
        String firstSegment = path.getSegmentNumber(1);

        assertEquals("/path", firstSegment);
    }

    @Test
    public void getsLastSegment() {
        String lastSegment = path.getLastSegment();

        assertEquals("/to", lastSegment);
    }
}