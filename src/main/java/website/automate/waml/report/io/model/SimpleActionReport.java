package website.automate.waml.report.io.model;

import website.automate.waml.io.model.action.Action;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleActionReport implements ActionReport {

    private Action action;
    
    private ActionStats stats;
    
    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @JsonIgnore
    @Override
    public ActionStats getStats() {
        return stats;
    }

    @Override
    public void setStats(ActionStats stats) {
        this.stats = stats;
    }
}
