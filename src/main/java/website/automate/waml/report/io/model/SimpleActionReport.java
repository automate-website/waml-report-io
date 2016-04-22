package website.automate.waml.report.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import website.automate.waml.io.model.action.Action;

@JsonPropertyOrder({"status", "message", "time", "path", "criteria"})
public class SimpleActionReport implements ActionReport {

    @JsonProperty("criteria")
    private Action action;

    private String path;

    private String message;

    private Double time = 0.0;

    private ExecutionStatus status;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
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
    public Double getTime() {
        return time;
    }

    @Override
    public void setTime(Double time) {
        this.time = time;
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
    public Action getAction() {
        return action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }
}
