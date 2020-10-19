package sample.httpsession;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import static org.mockito.Mockito.mock;

@SpringBootTest
class SampleHttpSessionApplicationTests {

	@Test
	void contextLoads() {
	}

	@TestConfiguration
	@EnableSpringHttpSession
	static class Config {

		@Bean
		public FindByIndexNameSessionRepository<?> sessionRepository() {
			return mock(FindByIndexNameSessionRepository.class);
		}

	}

}
