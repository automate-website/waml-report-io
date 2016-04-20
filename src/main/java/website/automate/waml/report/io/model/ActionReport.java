package website.automate.waml.report.io.model;

import website.automate.waml.io.model.action.Action;

public interface ActionReport {

    Action getAction();
    
    void setAction(Action action);
    
    ActionStats getStats();
    
    void setStats(ActionStats stats);
}
