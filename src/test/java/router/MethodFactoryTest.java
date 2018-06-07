package router;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MethodFactoryTest {

    private MethodFactory methodFactory;

    @Before
    public void createInstance() {
        methodFactory = new MethodFactory();
    }

    @Test
    public void createsGetHTTPMethod() {
        HTTPMethod getMethod = methodFactory.create("GET");

        assertTrue(getMethod instanceof GetMethod);
    }

    @Test
    public void createsHeadHTTPMethod() {
        HTTPMethod headMethod = methodFactory.create("HEAD");

        assertTrue(headMethod instanceof HeadMethod);
    }

    @Test
    public void createsPostHTTPMethod() {
        HTTPMethod postMethod = methodFactory.create("POST");

        assertTrue(postMethod instanceof PostMethod);
    }

    @Test
    public void createsPutHTTPMethod() {
        HTTPMethod putMethod = methodFactory.create("PUT");

        assertTrue(putMethod instanceof PutMethod);
    }

    @Test
    public void createsHeadHTTPMethodAsDefault() {
        HTTPMethod defaultMethod = methodFactory.create("SOMETHING");

        assertTrue(defaultMethod instanceof HeadMethod);
    }
}
