package sample.httpsession;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class SampleController {

	private final SessionScopedBean sessionScopedBean;

	SampleController(SessionScopedBean sessionScopedBean) {
		this.sessionScopedBean = sessionScopedBean;
	}

	@GetMapping(path = "/")
	String home(HttpServletRequest request, Authentication authentication, Model model) {
		model.addAttribute("isAuthenticated", (authentication != null) && authentication.isAuthenticated());
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

	@GetMapping(path = "/invalidate")
	String invalidate(HttpSession session) {
		session.invalidate();
		return "redirect:/";
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
