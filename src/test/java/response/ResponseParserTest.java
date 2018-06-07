package response;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static response.StatusLine.OK;

public class ResponseParserTest {
    @Test
    public void parsesStatusLine() {
        ResponseParser responseParser = new ResponseParser(OK);

        assertEquals("200 OK", responseParser.statusLine());
    }
}
