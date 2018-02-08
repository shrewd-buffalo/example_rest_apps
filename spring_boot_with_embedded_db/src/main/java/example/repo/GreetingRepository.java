package example.repo;

import example.entity.Greeting;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface GreetingRepository extends Repository<Greeting, Long> {

    List<Greeting> findAll();

    Greeting save(Greeting greeting);
}
