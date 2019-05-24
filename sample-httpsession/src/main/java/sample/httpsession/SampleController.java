package sample.httpsession;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {

	private final SessionRepository<? extends Session> sessionRepository;

	private final SessionScopedBean sessionScopedBean;

	public SampleController(SessionRepository<? extends Session> sessionRepository,
			SessionScopedBean sessionScopedBean) {
		this.sessionRepository = sessionRepository;
		this.sessionScopedBean = sessionScopedBean;
	}

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
