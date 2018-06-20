package router;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyLoggerTest {

    private Logger logger;

    @Before
    public void createLogger() {
        logger = new Logger();
    }

    @Test
    public void logsRequestLines() {
        logger.log("GET /");

        String log = logger.logs.get(0);

        assertEquals("GET /", log);
    }

    @Test
    public void getsAllLogs() {
        logger.log("GET /");
        logger.log("HEAD /");

        String logs = logger.getLogs();

        assertEquals("GET /\nHEAD /\n", logs);
    }
}