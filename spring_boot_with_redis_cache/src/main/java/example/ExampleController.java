package example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.logging.Logger;

@Controller
@EnableAutoConfiguration
public class ExampleController {

    private static final Logger LOGGER = Logger.getLogger(ExampleController.class.getName());
    private final static String GREETING_KEY = "greeting";

    private static final String DEFAULT_GREETING = "Hello World!";
    private Jedis jedis;

    public ExampleController() {
        jedis = new Jedis("192.168.99.100", 6379);
    }

    @RequestMapping("/")
    @ResponseBody
    String greet() {
        if( jedis.exists(GREETING_KEY))
        {
            LOGGER.info("Successful cache hit");
            return jedis.get(GREETING_KEY);
        }
        LOGGER.info("Cache miss");

        return DEFAULT_GREETING;
    }

    @PostMapping
    @RequestMapping("/change-greeting")
    @ResponseBody
    void changeGreeting( @RequestBody final String body) {
        LOGGER.info(String.format( "Setting cache entry '%s' to '%s'",GREETING_KEY, body ));

        // Remove any current value (if it doesn't exist, nothing happens)
        jedis.del(GREETING_KEY);

        /*
            Set value and expire in 5 seconds

            NX - Only set the key if it does not already exist.
            XX - Only set the key if it already exist

            EX = seconds
            PX = milliseconds
         */

        jedis.set(GREETING_KEY, body, "NX", "EX", 5);
    }

}
