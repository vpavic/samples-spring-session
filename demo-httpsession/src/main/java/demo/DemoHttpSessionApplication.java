package demo;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DemoHttpSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHttpSessionApplication.class, args);
	}

	@Autowired
	private SessionScopedBean sessionScopedBean;

	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String home(HttpSession session, Model model) {
		model.addAttribute("sessionType", session.getClass().getName());
		model.addAttribute("sessionId", session.getId());
		model.addAttribute("sessionCreationTime", Instant.ofEpochMilli(session.getCreationTime()));
		model.addAttribute("sessionLastAccessedTime", Instant.ofEpochMilli(session.getLastAccessedTime()));
		model.addAttribute("sessionMaxInactiveInterval", Duration.ofSeconds(session.getMaxInactiveInterval()));
		model.addAttribute("sessionAttributes", Collections.list(session.getAttributeNames()));
		return "home";
	}

	@GetMapping(path = "/scoped-bean")
	public String scopedBean() {
		this.sessionScopedBean.invoke();
		return "redirect:/";
	}

	@GetMapping(path = "/bind")
	public String bind(HttpSession session) {
		session.setAttribute("bindingListener", new LoggingHttpSessionBindingListener());
		return "redirect:/";
	}

	@GetMapping(path = "/unbind")
	public String unbind(HttpSession session) {
		session.removeAttribute("bindingListener");
		return "redirect:/";
	}

}
