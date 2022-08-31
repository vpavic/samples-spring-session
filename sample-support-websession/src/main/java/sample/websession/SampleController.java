package sample.websession;

import org.springframework.security.core.Authentication;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@Controller
class SampleController {

	private final ReactiveSessionRepository<? extends Session> sessionRepository;

	SampleController(ReactiveSessionRepository<? extends Session> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@GetMapping(path = "/")
	Mono<String> home(ServerWebExchange exchange, Authentication authentication, Model model) {
		model.addAttribute("isAuthenticated", (authentication != null) && authentication.isAuthenticated());
		model.addAttribute("sessionRepositoryType", this.sessionRepository.getClass().getName());
		return exchange.getSession().doOnNext(session -> {
			model.addAttribute("sessionId", session.getId());
			model.addAttribute("sessionCreationTime", session.getCreationTime());
			model.addAttribute("sessionLastAccessedTime", session.getLastAccessTime());
			model.addAttribute("sessionMaxInactiveInterval", session.getMaxIdleTime());
			model.addAttribute("sessionAttributes", session.getAttributes().keySet());
		}).then(Mono.just("home"));
	}

	@GetMapping(path = "/invalidate")
	Mono<String> invalidate(WebSession session) {
		return session.invalidate().then(Mono.just("redirect:/"));
	}

}
