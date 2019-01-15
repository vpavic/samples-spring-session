package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoHttpSessionApplicationTests {

	@Test
	public void contextLoads() {
	}

	@TestConfiguration
	@EnableSpringHttpSession
	static class Config {

		@Bean
		public FindByIndexNameSessionRepository sessionRepository() {
			return mock(FindByIndexNameSessionRepository.class);
		}

	}

}
