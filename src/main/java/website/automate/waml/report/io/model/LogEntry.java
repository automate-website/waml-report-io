package website.automate.waml.report.io.model;

import static java.text.MessageFormat.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"level", "time", "message"})
public class LogEntry {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public enum LogLevel {
    INFO,
    WARN,
    ERROR,
    DEBUG;
  }
  
  private final LogLevel level;
  
  private final Date time;
  
  private final String message;

  public LogEntry(LogLevel level, Date time, String message){
    super();
    this.level = level;
    this.time = time;
    this.message = message;
  }
  
  @JsonCreator
  public LogEntry(@JsonProperty("level") String level, @JsonProperty("time") String time, @JsonProperty("message") String message) {
    this(LogLevel.valueOf(level), deserialize(time), message);
  }

  private static Date deserialize(String time){
    try {
      return DATE_FORMAT.parse(time);
    } catch (ParseException e) {
      throw new IllegalArgumentException(format("Invalid date time {0} given.", time));
    }
  }
  
  private String serialize(Date time){
    return DATE_FORMAT.format(time);
  }
  
  public LogLevel getLevel() {
    return level;
  }

  public Date getTime() {
    return time;
  }
  

  @JsonProperty("time")
  public String getTimeStr(){
    return serialize(time);
  }
  
  public String getMessage() {
    return message;
  }
}
