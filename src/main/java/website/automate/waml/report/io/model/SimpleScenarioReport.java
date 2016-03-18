package website.automate.waml.report.io.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleScenarioReport implements ScenarioReport {

    @JsonIgnore
    private String name;
    
    private String message;
    
    private String path;

    private ExecutionStatus status = ExecutionStatus.SUCCESS;

    private Double time = 0.0;

    private Integer numActionPasses = 0;

    private Integer numActionFailures = 0;

    private List<ActionReport> actions = new ArrayList<>();
    
    @Override
    public void updateStats(){
        for(ActionReport action : actions){
            ExecutionStatus actionStatus = action.getStatus();
            status = ExecutionStatus.worstOf(status, actionStatus);
            setNumAction(actionStatus);
            time += action.getTime();
        }
    }
    
    private void setNumAction(ExecutionStatus actionStatus){
        if(actionStatus == ExecutionStatus.SUCCESS){
            this.numActionPasses++;
        } else {
            this.numActionFailures++;
        }
    }
    
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public ExecutionStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    @Override
    public Double getTime() {
        return time;
    }

    @Override
    public void setTime(Double time) {
        this.time = time;
    }

    @Override
    public Integer getNumActionPasses() {
        return numActionPasses;
    }

    @Override
    public void setNumActionPasses(Integer numActionPasses) {
        this.numActionPasses = numActionPasses;
    }

    @Override
    public Integer getNumActionFailures() {
        return numActionFailures;
    }

    @Override
    public void setNumActionFailures(Integer numActionFailures) {
        this.numActionFailures = numActionFailures;
    }

    @Override
    public List<ActionReport> getActions() {
        return actions;
    }

    @Override
    public void setActions(List<ActionReport> actions) {
        this.actions = actions;
    }
}
