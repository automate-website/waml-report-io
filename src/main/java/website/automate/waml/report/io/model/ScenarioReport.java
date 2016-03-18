package website.automate.waml.report.io.model;

import java.util.List;

public interface ScenarioReport {

    void updateStats();
    
    String getName();

    void setName(String name);

    String getMessage();

    void setMessage(String message);

    String getPath();

    void setPath(String path);

    ExecutionStatus getStatus();

    void setStatus(ExecutionStatus status);

    Double getTime();

    void setTime(Double time);

    Integer getNumActionPasses();

    void setNumActionPasses(Integer numActionPasses);

    Integer getNumActionFailures();

    void setNumActionFailures(Integer numActionFailures);

    List<ActionReport> getActions();

    void setActions(List<ActionReport> actions);
}
