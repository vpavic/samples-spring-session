package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;

@SpringBootApplication
@Controller
public class DemoWebSessionApplication {

	@Autowired
	private ReactiveSessionRepository<? extends Session> sessionRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoWebSessionApplication.class, args);
	}

	@GetMapping(path = "/")
	public String home(WebSession webSession, Model model) {
		model.addAttribute("sessionRepositoryType", this.sessionRepository.getClass().getName());
		model.addAttribute("sessionId", webSession.getId());
		model.addAttribute("sessionCreationTime", webSession.getCreationTime());
		model.addAttribute("sessionLastAccessedTime", webSession.getLastAccessTime());
		model.addAttribute("sessionMaxInactiveInterval", webSession.getMaxIdleTime());
		model.addAttribute("sessionAttributes", webSession.getAttributes().keySet());
		return "home";
	}

}
