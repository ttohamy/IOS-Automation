package helper;

import org.apache.logging.log4j.LogManager;

public class LoggerHelper {
    public org.apache.logging.log4j.Logger logger ;
    public LoggerHelper(){
        this.logger = LogManager.getLogger(getClass());;
    }
}
