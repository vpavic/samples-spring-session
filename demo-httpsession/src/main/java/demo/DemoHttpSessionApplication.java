package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

@SpringBootApplication
@Controller
public class DemoHttpSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHttpSessionApplication.class, args);
	}

	@Autowired
	private SessionScopedBean sessionScopedBean;

	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String home(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		model.addAttribute("sessionRepositoryType",
				request.getAttribute(SessionRepositoryFilter.SESSION_REPOSITORY_ATTR).getClass().getName());
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
