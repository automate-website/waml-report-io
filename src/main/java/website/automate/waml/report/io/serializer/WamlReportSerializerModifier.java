package website.automate.waml.report.io.serializer;

import website.automate.waml.io.model.action.Action;
import website.automate.waml.report.io.model.SimpleActionReport;
import website.automate.waml.report.io.model.SimpleScenarioReport;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class WamlReportSerializerModifier extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer){
      if (SimpleActionReport.class.isAssignableFrom(beanDesc.getBeanClass())){
          return new ActionReportSerializer(serializer);
      } else if(SimpleScenarioReport.class.isAssignableFrom(beanDesc.getBeanClass())){
          return new ScenarioReportSerializer(serializer);
      } else if(Action.class.isAssignableFrom(beanDesc.getBeanClass())){
          return new ActionSerializer(serializer);
      }
      return serializer;
    }
}
