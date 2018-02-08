package example;

import example.entity.Greeting;
import example.repo.GreetingRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@Transactional
@EnableAutoConfiguration
public class ExampleController {


    private final GreetingRepository repository;

    public ExampleController(GreetingRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    @ResponseBody
    String greet() {
        return repository.findAll().stream().map(Greeting::getGreeting).collect(Collectors.joining("\n"));
    }

    @PutMapping
    @RequestMapping("/add-greeting")
    @ResponseBody
    void addGreeting( @RequestBody final String body) {
        Greeting greeting = new Greeting( body );

        repository.save(greeting);
    }

}
