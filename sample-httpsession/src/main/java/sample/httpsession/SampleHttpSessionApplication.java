package sample.httpsession;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SampleHttpSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleHttpSessionApplication.class, args);
	}

	@Autowired
	private SessionRepository<? extends Session> sessionRepository;

	@Autowired
	private SessionScopedBean sessionScopedBean;

	@GetMapping(path = "/")
	public String home(HttpSession httpSession, Model model) {
		model.addAttribute("sessionRepositoryType", this.sessionRepository.getClass().getName());
		model.addAttribute("sessionId", httpSession.getId());
		model.addAttribute("sessionCreationTime", Instant.ofEpochMilli(httpSession.getCreationTime()));
		model.addAttribute("sessionLastAccessedTime", Instant.ofEpochMilli(httpSession.getLastAccessedTime()));
		model.addAttribute("sessionMaxInactiveInterval", Duration.ofSeconds(httpSession.getMaxInactiveInterval()));
		model.addAttribute("sessionAttributes", Collections.list(httpSession.getAttributeNames()));
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
