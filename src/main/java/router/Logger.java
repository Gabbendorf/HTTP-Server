package router;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    protected List<String> logs = new ArrayList<>();

    public void log(String requestLine) {
        logs.add(requestLine);
    }

    public String getLogs() {
        StringBuilder allLogs = new StringBuilder();
        for (String log : logs) {
            allLogs.append(log).append("\n");
        }
        return allLogs.toString();
    }
}
