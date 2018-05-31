package demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
public class DemoController {

	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String home(WebSession session) {
		return session.getId();
	}

}
