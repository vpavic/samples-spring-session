package demo;

import javax.servlet.http.HttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapIndexConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HttpSessionHazelcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpSessionHazelcastApplication.class, args);
	}

	@GetMapping(path = "/")
	public String home(HttpSession session) {
		return session.getId();
	}

	@Bean
	public Config config() {
		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		Config config = new Config();

		config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME)
				.addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(
						HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));

		return config;
	}

}
