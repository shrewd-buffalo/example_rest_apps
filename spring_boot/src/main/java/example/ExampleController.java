package example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class ExampleController {

    private String greeting = "Hello World!";

    @RequestMapping("/")
    @ResponseBody
    String greet() {
        return greeting;
    }

    @PostMapping
    @RequestMapping("/change-greeting")
    @ResponseBody
    void changeGreeting( @RequestBody final String body) {
        greeting = body;
    }

}
