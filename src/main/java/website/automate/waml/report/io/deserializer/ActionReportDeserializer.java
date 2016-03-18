package website.automate.waml.report.io.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import website.automate.waml.report.io.model.ActionReport;
import website.automate.waml.report.io.model.SimpleActionReport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ActionReportDeserializer extends StdDeserializer<ActionReport> {

    private static final long serialVersionUID = 1910302566160516127L;

    public ActionReportDeserializer() {
        super(ActionReport.class);
    }

    @Override
    public ActionReport deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);

        Iterator<Entry<String, JsonNode>> elementsIterator = root.fields();
        String key = null;
        JsonNode value = null;
        while (elementsIterator.hasNext()) {
            Entry<String, JsonNode> element = elementsIterator.next();
            key = element.getKey();
            value = element.getValue();
            break;
        }
        
        ActionReport actionReport = mapper.convertValue(value, SimpleActionReport.class);
        actionReport.setName(key);
        
        return actionReport;
    }
}
