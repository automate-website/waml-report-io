package website.automate.waml.report.io.deserializer;

import static java.util.Collections.singletonMap;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import website.automate.waml.io.model.ActionType;
import website.automate.waml.io.model.CriterionType;
import website.automate.waml.io.model.action.Action;
import website.automate.waml.io.model.action.StoreAction;
import website.automate.waml.report.io.model.ActionReport;
import website.automate.waml.report.io.model.ActionStats;
import website.automate.waml.report.io.model.SimpleActionReport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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
        String actionName = null;
        JsonNode actionValue = null;
        JsonNode statsValue = null;
        
        while (elementsIterator.hasNext()) {
            Entry<String, JsonNode> element = elementsIterator.next();
            String key = element.getKey();
            if(key.equals("stats")){
                statsValue = element.getValue();
            } else {
                actionName = key;
                actionValue = element.getValue();
            }
        }
        
        ActionType actionType = ActionType.findByName(actionName);
        Class<? extends Action> actionClazz = actionType.getClazz();
        ActionReport actionReport = new SimpleActionReport();
        
        if(isActionShortNotated(actionClazz, actionValue)){
            JsonNode wrapper = new ObjectNode(JsonNodeFactory.instance,
                    singletonMap(actionType.getDefaultCriteriaType().getName(),
                            actionValue));
            actionReport.setAction(mapper.treeToValue(wrapper, actionClazz));
        } else {
            actionReport.setAction(mapper.treeToValue(actionValue, actionClazz));
        }
        
        actionReport.setStats(mapper.treeToValue(statsValue, ActionStats.class));
        
        return actionReport;
    }
    
    private boolean isActionShortNotated(Class<? extends Action> actionClass, JsonNode object){
        if(object.isTextual()){
            return true;
        }
        if(actionClass == StoreAction.class){
            return !object.has(CriterionType.IF.getName())
                    && !object.has(CriterionType.UNLESS.getName())
                    && !object.has(CriterionType.VALUE.getName());
        }
        return !object.isObject();
    }
}
