package router;

import controllers.*;
import controllers.FormPage;
import controllers.fileSystem.FilePage;
import controllers.fileSystem.FileSystem;

import java.util.LinkedHashMap;
import java.util.Map;

public class Configuration {

    public Map<String, Controller> setControllers(Logger logger, FileSystem fileSystem) {
        Map<String, Controller> controllers = new LinkedHashMap<>();
        controllers.put("/", new HomePage(fileSystem));
        controllers.put("/redirect", new RedirectPage());
        controllers.put("/cookie", new CookiePage());
        controllers.put("/parameters", new ParameterPage());
        controllers.put("/logs", new LogsPage(logger));
        controllers.put("/eat_cookie", new CookiePage());
        controllers.put("/method_options", new MethodOptionsPage());
        controllers.put("/method_options2", new MethodOptions2Page());
        controllers.put("/form", new FormPage());
        controllers.put("/put-target", new PutTargetPage());
        controllers.put("/file1", new FilePage(fileSystem));
        controllers.put("/text-file.txt", new FilePage(fileSystem));
        controllers.put("/coffee", new TeaPage());
        controllers.put("/tea", new TeaPage());
        controllers.put("/patch-content.txt", new FilePage(fileSystem));
        return controllers;
    }
}
