package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@SpringBootApplication
@RestController
public class WebFluxRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxRedisApplication.class, args);
	}

	@GetMapping(path = "/")
	public String home(WebSession session) {
		session.start();
		return session.getId();
	}

}
