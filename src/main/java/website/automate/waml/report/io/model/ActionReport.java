package website.automate.waml.report.io.model;

public interface ActionReport {

    String getName();

    void setName(String name);

    String getPath();
    
    void setPath(String path);

    String getMessage();

    void setMessage(String message);
    
    ExecutionStatus getStatus();

    void setStatus(ExecutionStatus status);

    Double getTime();

    void setTime(Double time);
    
    void setTime(Long startTimeInMillis);
}
