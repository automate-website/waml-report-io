package website.automate.waml.report.io;

import website.automate.waml.report.io.deserializer.ActionReportDeserializer;
import website.automate.waml.report.io.deserializer.ScenarioReportDeserializer;
import website.automate.waml.report.io.model.ActionReport;
import website.automate.waml.report.io.model.ScenarioReport;
import website.automate.waml.report.io.serializer.WamlReportSerializerModifier;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class WamlReportConfig {
    
    private static final String MODULE_NAME = "waml-report-io";

    private static final WamlReportConfig INSTANCE = new WamlReportConfig();
    
    private ObjectMapper mapper = createMapper();
    
    public static WamlReportConfig getInstance(){
        return INSTANCE;
    }
    
    private ObjectMapper createMapper(){
        SimpleModule module = new SimpleModule(MODULE_NAME, Version.unknownVersion());
        module.setSerializerModifier(new WamlReportSerializerModifier());
        
        module.addDeserializer(ScenarioReport.class, new ScenarioReportDeserializer());
        module.addDeserializer(ActionReport.class, new ActionReportDeserializer());
        
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(module);
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper;
    }
    
    public ObjectMapper getMapper(){
        return mapper;
    }
}
