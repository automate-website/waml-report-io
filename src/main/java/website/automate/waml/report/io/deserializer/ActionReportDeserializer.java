package website.automate.waml.report.io.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import website.automate.waml.io.model.ActionType;
import website.automate.waml.io.model.action.Action;
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
        ObjectMapper mapper = ObjectMapper.class.cast(jsonParser.getCodec());
        ObjectNode root = ObjectNode.class.cast(mapper.readTree(jsonParser));

        Iterator<Entry<String, JsonNode>> elementsIterator = root.fields();
        String actionName = null;
        ObjectNode actionReportValue = null;
        
        while (elementsIterator.hasNext()) {
            Entry<String, JsonNode> element = elementsIterator.next();
            actionName = element.getKey();
            actionReportValue = ObjectNode.class.cast(element.getValue());
            break;
        }
        
        JsonNode actionValue = actionReportValue.remove("criteria");
        ActionType actionType = ActionType.findByName(actionName);
        Class<? extends Action> actionClazz = actionType.getClazz();
        ActionReport actionReport = mapper.treeToValue(actionReportValue, SimpleActionReport.class);
        actionReport.setAction(mapper.treeToValue(actionValue, actionClazz));
        
        return actionReport;
    }
}
