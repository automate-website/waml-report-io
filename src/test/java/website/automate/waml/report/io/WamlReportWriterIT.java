package website.automate.waml.report.io;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import website.automate.waml.io.model.Scenario;
import website.automate.waml.io.model.action.OpenAction;
import website.automate.waml.report.io.model.ActionReport;
import website.automate.waml.report.io.model.ScenarioReport;
import website.automate.waml.report.io.model.SimpleActionReport;
import website.automate.waml.report.io.model.ExecutionStatus;
import website.automate.waml.report.io.model.LogEntry;
import website.automate.waml.report.io.model.SimpleScenarioReport;
import website.automate.waml.report.io.model.WamlReport;

public class WamlReportWriterIT {

    private WamlReportWriter writer = new WamlReportWriter();
    
    @Test
    public void wamlReportIsSerialized() throws Exception {
        WamlReport wamlReport = createWamlReport(asList(
                createScenarioReport("test-scenario", "/var/waml/test-scenario.yaml", asList(
                        createActionReport("test-scenario", ExecutionStatus.SUCCESS, 1.0)))));
        
        InputStream expectedWamlReportStreamStream = getSystemResourceAsStream("./website/automate/waml/report/io/waml-report.yaml");
        
        ByteArrayOutputStream actualWamlReportStream = new ByteArrayOutputStream();
        writer.write(actualWamlReportStream, wamlReport);
        
        assertEquals(IOUtils.toString(expectedWamlReportStreamStream, "UTF-8"), actualWamlReportStream.toString("UTF-8"));
    }
    
    private ActionReport createActionReport(String path, ExecutionStatus status, Double time){
        SimpleActionReport actionReport = new SimpleActionReport();
        OpenAction openAction = new OpenAction();
        openAction.setUrl("https://wikipedia.com");
        actionReport.setStatus(status);
        actionReport.setTime(time);
        actionReport.setPath(path);
        actionReport.setAction(openAction);
        actionReport.setLogEntries(asList(createLogEntry()));
        return actionReport;
    }
    
    private LogEntry createLogEntry(){
      return new LogEntry("INFO", "2007-12-24 18:21:00", "Hallo");
    }
    
    private ScenarioReport createScenarioReport(String name, String path, List<ActionReport> actionReports){
        ScenarioReport scenarioReport = new SimpleScenarioReport();
        Scenario scenario = new Scenario();
        scenario.setName(name);
        scenarioReport.setSteps(actionReports);
        scenarioReport.setScenario(scenario);
        scenarioReport.setPath(path);
        return scenarioReport;
    }
    
    private WamlReport createWamlReport(List<ScenarioReport> scenarioReports){
        WamlReport wamlReport = new WamlReport();
        wamlReport.setScenarios(scenarioReports);
        wamlReport.updateStats();
        return wamlReport;
    }
}
