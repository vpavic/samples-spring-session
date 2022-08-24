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
class SampleController {

	private final SessionRepository<? extends Session> sessionRepository;

	private final SessionScopedBean sessionScopedBean;

	SampleController(SessionRepository<? extends Session> sessionRepository, SessionScopedBean sessionScopedBean) {
		this.sessionRepository = sessionRepository;
		this.sessionScopedBean = sessionScopedBean;
	}

	@GetMapping(path = "/")
	String home(HttpSession httpSession, Model model) {
		model.addAttribute("sessionRepositoryType", this.sessionRepository.getClass().getName());
		model.addAttribute("sessionId", httpSession.getId());
		model.addAttribute("sessionCreationTime", Instant.ofEpochMilli(httpSession.getCreationTime()));
		model.addAttribute("sessionLastAccessedTime", Instant.ofEpochMilli(httpSession.getLastAccessedTime()));
		model.addAttribute("sessionMaxInactiveInterval", Duration.ofSeconds(httpSession.getMaxInactiveInterval()));
		model.addAttribute("sessionAttributes", Collections.list(httpSession.getAttributeNames()));
		return "home";
	}

	@GetMapping(path = "/scoped-bean")
	String scopedBean() {
		this.sessionScopedBean.invoke();
		return "redirect:/";
	}

	@GetMapping(path = "/bind")
	String bind(HttpSession session) {
		session.setAttribute("bindingListener", new LoggingHttpSessionBindingListener());
		return "redirect:/";
	}

	@GetMapping(path = "/unbind")
	String unbind(HttpSession session) {
		session.removeAttribute("bindingListener");
		return "redirect:/";
	}

}
