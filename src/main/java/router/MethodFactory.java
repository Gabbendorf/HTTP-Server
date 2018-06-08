package router;

public class MethodFactory {

    public HTTPMethod create(String method) {
        switch (method) {
            case "GET":
                return new GetMethod();
            case "HEAD":
                return new HeadMethod();
            case "POST":
                return new PostMethod();
            case "PUT":
                return new PutMethod();
            case "OPTIONS":
                return new OptionsMethod();
            default:
                return new BogusMethod();
        }
    }
}
