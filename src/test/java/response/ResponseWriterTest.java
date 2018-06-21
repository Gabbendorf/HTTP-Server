package response;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class ResponseWriterTest {

    @Test
    public void writesResponseToStream() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResponseWriter writer = new ResponseWriter(outputStream);

        writer.write("HTTP/1.1 200 OK");

        assertEquals("HTTP/1.1 200 OK\n", outputStream.toString());
    }
}
