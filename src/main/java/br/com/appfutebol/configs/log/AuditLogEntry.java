package br.com.appfutebol.configs.log;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditLogEntry {
    private String currentClass;
    private LocalDateTime dateTime;
    private AuditResult result;
    private String methodName;
    private String action;

    public String logMessage(){
        String message = String.format("""
          An event occurred on Class %s= Action: %s, timestamp: %s, Method: %s, Result: %s
          """, this.currentClass,this.action,this.dateTime, this.methodName, this.result);
        return message;
    }
    public String logMessage(Throwable ex){
        String message = String.format("""
          An event occurred on Class %s= Action: %s, timestamp: %s, Method: %s, Result: %s, errorMessage: %s
          """, this.currentClass,this.action,this.dateTime, this.methodName, this.result, ex.getMessage());
        return message;
    }
}
