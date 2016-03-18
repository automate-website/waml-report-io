package website.automate.waml.report.io.serializer;

import java.io.IOException;

import website.automate.waml.report.io.model.SimpleScenarioReport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ScenarioReportSerializer extends StdSerializer<SimpleScenarioReport> implements ResolvableSerializer {
    
    private static final long serialVersionUID = 4531209051287437138L;

    @SuppressWarnings("rawtypes")
    private final JsonSerializer defaultSerializer;
    
    @SuppressWarnings("rawtypes")
    public ScenarioReportSerializer(JsonSerializer defaultSerializer) {
        super(SimpleScenarioReport.class);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void resolve(SerializerProvider provider)
            throws JsonMappingException {
        ((ResolvableSerializer) defaultSerializer).resolve(provider);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void serialize(SimpleScenarioReport scenarioReport, JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeFieldName(scenarioReport.getName());
        defaultSerializer.serialize(scenarioReport, generator, provider);
        generator.writeEndObject();
    }

}
