package website.automate.waml.report.io.serializer;

import java.io.IOException;

import website.automate.waml.io.model.ActionType;
import website.automate.waml.report.io.model.SimpleActionReport;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ActionReportSerializer extends StdSerializer<SimpleActionReport> implements ResolvableSerializer {
    
    private static final long serialVersionUID = 4531209051287437138L;

    @SuppressWarnings("rawtypes")
    private final JsonSerializer defaultSerializer;
    
    @SuppressWarnings("rawtypes")
    public ActionReportSerializer(JsonSerializer defaultSerializer) {
        super(SimpleActionReport.class);
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void resolve(SerializerProvider provider)
            throws JsonMappingException {
        ((ResolvableSerializer) defaultSerializer).resolve(provider);
    }

    @Override
    public void serialize(SimpleActionReport actionReport, JsonGenerator generator,
            SerializerProvider provider) throws IOException {
        ActionType actionType = ActionType.findByClazz(actionReport.getAction().getClass());
        generator.writeStartObject();
        generator.writeObjectField(actionType.getName(), actionReport.getAction());
        generator.writeObjectField("stats", actionReport.getStats());
        generator.writeEndObject();
    }

}
