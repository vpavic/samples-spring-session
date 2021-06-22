package sample.websession;

import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;

@Controller
class SampleController {

	private final ReactiveSessionRepository<? extends Session> sessionRepository;

	SampleController(ReactiveSessionRepository<? extends Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@GetMapping(path = "/")
	String home(WebSession webSession, Model model) {
		model.addAttribute("sessionRepositoryType", this.sessionRepository.getClass().getName());
		model.addAttribute("sessionId", webSession.getId());
		model.addAttribute("sessionCreationTime", webSession.getCreationTime());
		model.addAttribute("sessionLastAccessedTime", webSession.getLastAccessTime());
		model.addAttribute("sessionMaxInactiveInterval", webSession.getMaxIdleTime());
		model.addAttribute("sessionAttributes", webSession.getAttributes().keySet());
		return "home";
	}

}
