package website.automate.waml.report.io.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import website.automate.waml.io.model.Scenario;
import website.automate.waml.report.io.model.ScenarioReport;
import website.automate.waml.report.io.model.SimpleScenarioReport;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ScenarioReportDeserializer extends StdDeserializer<ScenarioReport> {

    private static final long serialVersionUID = 1910302566160516127L;

    public ScenarioReportDeserializer() {
        super(ScenarioReport.class);
    }

    @Override
    public ScenarioReport deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper)jsonParser.getCodec();
        ObjectNode root = (ObjectNode) mapper.readTree(jsonParser);

        Iterator<Entry<String, JsonNode>> elementsIterator = root.fields();
        String scenarioName = null;
        ObjectNode scenarioReportValue = null;
        while (elementsIterator.hasNext()) {
            Entry<String, JsonNode> element = elementsIterator.next();
            scenarioName = element.getKey();
            scenarioReportValue = ObjectNode.class.cast(element.getValue());
            break;
        }
        
        JsonNode scenarioValue = scenarioReportValue.remove("criteria");
        Scenario scenario = mapper.convertValue(scenarioValue, Scenario.class);
        scenario.setName(scenarioName);

        ScenarioReport scenarioReport = mapper.convertValue(scenarioReportValue, SimpleScenarioReport.class);
        scenarioReport.setScenario(scenario);
        
        return scenarioReport;
    }
}
