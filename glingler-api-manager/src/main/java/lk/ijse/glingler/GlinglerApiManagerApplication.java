package lk.ijse.glingler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlinglerApiManagerApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(GlinglerApiManagerApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(GlinglerApiManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ThreadContext.put("REQUEST_ID", "00000000000000000000000000000000");
        ThreadContext.pop();

        LOGGER.debug("Initializing server security configurations...");

    }
}
