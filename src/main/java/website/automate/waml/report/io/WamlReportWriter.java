package website.automate.waml.report.io;

import java.io.OutputStream;

import website.automate.waml.report.io.model.WamlReport;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WamlReportWriter {

    private ObjectMapper objectMapper = WamlReportConfig.getInstance().getMapper();
    
    public void write(OutputStream scenarioStream, WamlReport report){
        try {
            objectMapper.writeValue(scenarioStream, report);
        } catch (Exception e) {
            throw new WamlReportSerializationException("Waml report could not be serialized.", e);
        }
    }
}
