import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class HelloLog {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String userInput = "${jndi:http://localhost/AAAA/BBBB}";

        // passing user input into the logger
        logger.info("Test: {}", userInput);
    }
}

