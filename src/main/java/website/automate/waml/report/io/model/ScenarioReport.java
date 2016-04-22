package website.automate.waml.report.io.model;

import java.util.List;

import website.automate.waml.io.model.Scenario;

public interface ScenarioReport {

    Scenario getScenario();
    
    void setScenario(Scenario scenario);
    
    void updateStats();
    
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
