package website.automate.waml.report.io.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import website.automate.waml.report.io.model.LogEntry.LogLevel;

public class LogEntryTest {

    @Test
    public void infoIsIncludedIntoDebug(){
        assertTrue(LogEntry.isIncluded(LogLevel.DEBUG, LogLevel.INFO));
    }
    
    @Test
    public void warnIsIncludedIntoDebug(){
        assertTrue(LogEntry.isIncluded(LogLevel.DEBUG, LogLevel.WARN));
    }
    
    @Test
    public void errorIsIncludedIntoDebug(){
        assertTrue(LogEntry.isIncluded(LogLevel.DEBUG, LogLevel.ERROR));
    }
    
    @Test
    public void debugIsIncludedIntoDebug(){
        assertTrue(LogEntry.isIncluded(LogLevel.DEBUG, LogLevel.DEBUG));
    }
    
    @Test
    public void infoIsIncludedIntoInfo(){
        assertTrue(LogEntry.isIncluded(LogLevel.INFO, LogLevel.INFO));
    }
    
    @Test
    public void warnIsIncludedIntoInfo(){
        assertTrue(LogEntry.isIncluded(LogLevel.INFO, LogLevel.WARN));
    }
    
    @Test
    public void errorIsIncludedIntoInfo(){
        assertTrue(LogEntry.isIncluded(LogLevel.INFO, LogLevel.ERROR));
    }
    
    @Test
    public void warnIsIncludedIntoWarn(){
        assertTrue(LogEntry.isIncluded(LogLevel.WARN, LogLevel.WARN));
    }
    
    @Test
    public void errorIsIncludedIntoWarn(){
        assertTrue(LogEntry.isIncluded(LogLevel.WARN, LogLevel.ERROR));
    }
    
    @Test
    public void errorIsIncludedIntoError(){
        assertTrue(LogEntry.isIncluded(LogLevel.ERROR, LogLevel.ERROR));
    }
    
    @Test
    public void debugIsNotIncludedIntoError(){
        assertFalse(LogEntry.isIncluded(LogLevel.ERROR, LogLevel.DEBUG));
    }
    
    @Test
    public void infoIsNotIncludedIntoError(){
        assertFalse(LogEntry.isIncluded(LogLevel.ERROR, LogLevel.INFO));
    }
    
    @Test
    public void warnIsNotIncludedIntoError(){
        assertFalse(LogEntry.isIncluded(LogLevel.ERROR, LogLevel.WARN));
    }
}
