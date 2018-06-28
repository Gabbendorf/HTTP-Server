package request;

import java.util.ArrayList;
import java.util.List;

public enum HTTPMethod {

    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    INVALID("INVALID"),
    DELETE("DELETE");

    public final String method;

    HTTPMethod(String value) {
        method = value;
    }

    public static HTTPMethod create(String method) {
        if (isNotValid(method)) {
            return INVALID;
        }
        return HTTPMethod.valueOf(method);
    }

    private static boolean isNotValid(String method) {
        return !allMethods().contains(method);
    }

    private static List<String> allMethods() {
        List<String> methods = new ArrayList<>();
        for (HTTPMethod httpMethod : HTTPMethod.values()) {
            methods.add(httpMethod.method);
        }
        return methods;
    }
}
