package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@SpringBootApplication
@RestController
public class DemoWebSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebSessionApplication.class, args);
	}

	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String home(WebSession session) {
		return session.getId();
	}

}
