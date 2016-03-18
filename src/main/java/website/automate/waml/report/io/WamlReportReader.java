package website.automate.waml.report.io;

import java.io.InputStream;

import website.automate.waml.report.io.model.WamlReport;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WamlReportReader {

    private ObjectMapper objectMapper = WamlReportConfig.getInstance().getMapper();
    
    public WamlReport read(InputStream reportStream){
        try {
            return objectMapper.readValue(reportStream, WamlReport.class);
        } catch (Exception e) {
            throw new WamlReportDeserializationException("Waml report could not be deserialized.", e);
        }
    }
}
