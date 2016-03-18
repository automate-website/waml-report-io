package website.automate.waml.report.io.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SimpleActionReport implements ActionReport {

    @JsonIgnore
    private String name;

    private String path;

    private String message;
    
    private Double time = 0.0;

    private ExecutionStatus status;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
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

    @JsonSetter
    @Override
    public void setTime(Double time) {
        this.time = time;
    }
    
    @JsonIgnore
    public void setTime(Long startTimeInMillis){
        this.time = (System.currentTimeMillis() - startTimeInMillis) / 1000.0; 
    }
}
