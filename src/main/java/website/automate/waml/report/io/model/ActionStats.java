package website.automate.waml.report.io.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ActionStats {

    private String path;

    private String message;
    
    private Double time = 0.0;

    private ExecutionStatus status;
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        this.status = status;
    }

    public Double getTime() {
        return time;
    }

    @JsonSetter
    public void setTime(Double time) {
        this.time = time;
    }
    
    @JsonIgnore
    public void setTime(Long startTimeInMillis){
        this.time = (System.currentTimeMillis() - startTimeInMillis) / 1000.0; 
    }
}
