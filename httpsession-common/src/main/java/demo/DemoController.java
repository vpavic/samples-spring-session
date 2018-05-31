package demo;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

	@GetMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String home(HttpSession session) {
		return session.getId();
	}

	@GetMapping(path = "/bind")
	public String bind(HttpSession session) {
		session.setAttribute("bind", new LoggingHttpSessionBindingListener());
		return "redirect:/";
	}

	@GetMapping(path = "/unbind")
	public String unbind(HttpSession session) {
		session.removeAttribute("bind");
		return "redirect:/";
	}

}
