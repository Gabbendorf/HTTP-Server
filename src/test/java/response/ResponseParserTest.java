package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseParserTest {

    @Test
    public void parsesStatusCode() {
        ResponseParser responseParser = new ResponseParser("200 OK");

        String statusCode = responseParser.statusCode();

        assertEquals("200", statusCode);
    }

    @Test
    public void parsesStatusMessage() {
        ResponseParser responseParser = new ResponseParser("200 OK");

        String statusMessage = responseParser.statusMessage();

        assertEquals("OK", statusMessage);
    }
}
