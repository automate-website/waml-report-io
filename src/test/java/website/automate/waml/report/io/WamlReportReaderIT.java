package website.automate.waml.report.io;

import static java.lang.ClassLoader.getSystemResourceAsStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static website.automate.waml.report.io.model.ExecutionStatus.SUCCESS;

import java.io.InputStream;

import org.junit.Test;

import website.automate.waml.report.io.model.ScenarioReport;
import website.automate.waml.report.io.model.WamlReport;

public class WamlReportReaderIT {

    private WamlReportReader reader = new WamlReportReader();
    
    @Test
    public void wamlReportIsDeserialized(){
        InputStream expectedWamlReportStreamStream = getSystemResourceAsStream("./website/automate/waml/report/io/waml-report.yaml");
        
        WamlReport wamlReport = reader.read(expectedWamlReportStreamStream);
        
        assertThat(wamlReport.getStatus(), is(SUCCESS));
        ScenarioReport scenarioReport = wamlReport.getScenarios().get(0);
        assertThat(scenarioReport.getStatus(), is(SUCCESS));
        assertThat(scenarioReport.getPath(), is("/var/waml/test-scenario.yaml"));
        assertThat(scenarioReport.getScenario().getName(), is("test-scenario"));
        assertThat(scenarioReport.getActions().size(), is(1));
    }
}
