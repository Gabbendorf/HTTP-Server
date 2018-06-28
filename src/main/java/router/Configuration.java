package router;

import controllers.*;
import controllers.FormController;
import controllers.fileSystem.FileController;
import controllers.fileSystem.FileSystem;

import java.util.LinkedHashMap;
import java.util.Map;

public class Configuration {

    public Map<String, Controller> setControllers(Logger logger, FileSystem fileSystem) {
        Map<String, Controller> controllers = new LinkedHashMap<>();
        controllers.put("/", new HomeController(fileSystem));
        controllers.put("/redirect", new RedirectController());
        controllers.put("/cookie", new CookieController());
        controllers.put("/parameters", new ParameterController());
        controllers.put("/logs", new LogsController(logger));
        controllers.put("/eat_cookie", new CookieController());
        controllers.put("/method_options", new MethodOptionsController());
        controllers.put("/method_options2", new MethodOptions2Controller());
        controllers.put("/form", new FormController());
        controllers.put("/cat-form", new CatFormController(fileSystem));
        controllers.put("/put-target", new PutTargetController());
        controllers.put("/file1", new FileController(fileSystem));
        controllers.put("/text-file.txt", new FileController(fileSystem));
        controllers.put("/coffee", new TeaController());
        controllers.put("/tea", new TeaController());
        controllers.put("/patch-content.txt", new FileController(fileSystem));
        return controllers;
    }
}
