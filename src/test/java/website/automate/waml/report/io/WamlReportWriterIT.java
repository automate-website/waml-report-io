package website.automate.waml.report.io;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import website.automate.waml.io.model.action.OpenAction;
import website.automate.waml.report.io.model.ActionReport;
import website.automate.waml.report.io.model.ActionStats;
import website.automate.waml.report.io.model.ScenarioReport;
import website.automate.waml.report.io.model.SimpleActionReport;
import website.automate.waml.report.io.model.ExecutionStatus;
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
        ActionStats actionStats = new ActionStats();
        actionStats.setStatus(status);
        actionStats.setTime(time);
        actionStats.setPath(path);
        actionReport.setStats(actionStats);
        actionReport.setAction(openAction);
        return actionReport;
    }
    
    private ScenarioReport createScenarioReport(String name, String path, List<ActionReport> actionReports){
        ScenarioReport scenarioReport = new SimpleScenarioReport();
        scenarioReport.setActions(actionReports);
        scenarioReport.setName(name);
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
