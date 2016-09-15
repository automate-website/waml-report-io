package website.automate.waml.report.io.model;

import java.util.List;

import website.automate.waml.io.model.action.Action;

public interface ActionReport {

    Action getAction();
    
    void setAction(Action action);

    String getPath();

    void setPath(String path);

    String getMessage();

    void setMessage(String message);

    Double getTime();

    void setTime(Double time);

    ExecutionStatus getStatus();

    void setStatus(ExecutionStatus status);
    
    List<LogEntry> getLogEntries();
    
    void setLogEntries(List<LogEntry> logEntries);
}
