package demo;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpSessionJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpSessionJdbcApplication.class, args);
	}

	@GetMapping(path = "/")
	public String home(HttpSession session) {
		return session.getId();
	}

}
