package demo;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ServletRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletRedisApplication.class, args);
	}

	@ResponseBody
	@GetMapping(path = "/")
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
